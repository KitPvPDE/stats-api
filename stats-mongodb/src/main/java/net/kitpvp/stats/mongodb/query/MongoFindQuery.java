package net.kitpvp.stats.mongodb.query;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.mongodb.api.async.AsyncTask;
import net.kitpvp.stats.mongodb.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

import static com.mongodb.assertions.Assertions.isTrue;
import static com.mongodb.assertions.Assertions.notNull;


public final class MongoFindQuery implements AsyncTask, Iterable<StatsReader> {

    private final Database database;
    private final Collection collection;
    private Bson filter, sort;
    private int limit, skip;

    public MongoFindQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.limit = this.skip = 0;
    }

    public final MongoFindQuery filter(@NotNull Bson filter) {
        notNull("filter", filter);
        isTrue("filter already set", this.filter == null);
        this.filter = filter;
        return this;
    }

    public final MongoFindQuery filters(@NotNull Bson... filters) {
        notNull("filters", filters);
        return this.filter(Filters.and(filters));
    }

    public final MongoFindQuery sort(@NotNull Bson sort) {
        notNull("sort", sort);
        this.sort = sort;
        return this;
    }

    public final MongoFindQuery limit(int limit) {
        this.limit = limit;
        return this;
    }

    public final MongoFindQuery skip(int skip) {
        this.skip = skip;
        return this;
    }

    public final @NotNull MongoIterable<StatsReader> find() {
        Log.debug("Executing find query for {0} (Limit: {1}, Skip: {2}, Sort: {3})", this.filter, this.limit, this.skip, this.sort);

        return this.query();
    }

    public final void findAsync(Consumer<MongoIterable<StatsReader>> callback) {
        this.findAsync(callback, Executors.DIRECT);
    }

    public final void findAsync(Consumer<MongoIterable<StatsReader>> callback, Executor executor) {
        this.executeTaskAsync(this::find, callback, executor);
    }

    public final @Nullable StatsReader first() {
        return this.query().first();
    }

    public final void firstAsync(Consumer<StatsReader> callback) {
        this.firstAsync(callback, Executors.DIRECT);
    }

    public final void firstAsync(Consumer<StatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::first, callback, executor);
    }

    public final @NotNull MongoCursor<StatsReader> iterator() {
        return this.query().cursor();
    }

    private MongoIterable<StatsReader> query() {
        Stats.checkForMainThread();

        FindIterable<Document> iterable = this.database.getCollection(this.collection)
                .find(this.filter)
                .limit(this.limit)
                .skip(this.skip)
                .sort(this.sort);

        return iterable.map(BsonStatsReader::new);
    }
}
