package net.kitpvp.stats.mongodb.query;

import com.google.common.base.Preconditions;
import com.mongodb.Function;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.api.async.AsyncTask;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import net.kitpvp.stats.mongodb.query.sort.MongoSort;
import net.kitpvp.stats.query.FindQuery;
import net.kitpvp.stats.query.filter.Filter;
import net.kitpvp.stats.query.sort.Sort;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.stream.Stream;


public final class MongoFindQuery implements FindQuery<MongoStatsReader>, AsyncTask {

    private final Database database;
    private final Collection collection;
    private final MongoStatsReader criteria, sort;
    private final Function<Document, MongoStatsReader> mapper = MongoStatsReader::new;
    private int limit, skip;

    public MongoFindQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.criteria = new MongoStatsReader();
        this.sort = new MongoStatsReader();
        this.limit = this.skip = 0;
    }

    @Override
    @SafeVarargs
    public final MongoFindQuery filter(@NotNull Filter<MongoStatsReader>... filters) {
        Preconditions.checkArgument(filters.length > 0, "Zero filters specified");
        Stream.of(filters).forEach(filter -> filter.append(this.criteria));
        return this;
    }

    public final @NotNull MongoFindQuery sort(MongoSort... filters) {
        return this.sort((Sort<MongoStatsReader>[]) filters);
    }

    @Override
    @SafeVarargs
    public final @NotNull MongoFindQuery sort(Sort<MongoStatsReader>... sorts) {
        Preconditions.checkArgument(sorts.length > 0, "Zero sorts specified");
        Stream.of(sorts).forEach(filter -> filter.append(this.sort));
        return this;
    }

    @Override
    public final @NotNull MongoFindQuery limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public final @NotNull MongoFindQuery skip(int skip) {
        this.skip = skip;
        return this;
    }

    public final @NotNull MongoIterable<MongoStatsReader> find() {
        Log.debug("Executing find query for {0} (Limit: {1}, Skip: {2}, Sort: {3})", this.criteria.bson(), this.limit, this.skip, this.sort.bson());

        return this.query();
    }

    public final void findAsync(Consumer<MongoIterable<MongoStatsReader>> callback) {
        this.findAsync(callback, Executors.DIRECT);
    }

    public final void findAsync(Consumer<MongoIterable<MongoStatsReader>> callback, Executor executor) {
        this.executeTaskAsync(this::find, callback, executor);
    }

    public final @Nullable MongoStatsReader first() {
        return this.query().first();
    }

    public final void firstAsync(Consumer<MongoStatsReader> callback) {
        this.firstAsync(callback, Executors.DIRECT);
    }

    public final void firstAsync(Consumer<MongoStatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::first, callback, executor);
    }

    @Override
    public final @NotNull MongoCursor<MongoStatsReader> iterator() {
        return this.query().cursor();
    }

    private MongoIterable<MongoStatsReader> query() {
        Stats.checkForMainThread();

        FindIterable<Document> iterable = this.database.getCollection(this.collection).
                find(this.criteria.bson()).
                limit(this.limit).
                skip(this.skip).
                sort(this.sort.bson());

        return iterable.map(this.mapper);
    }
}
