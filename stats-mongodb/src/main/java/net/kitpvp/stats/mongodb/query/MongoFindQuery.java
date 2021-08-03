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
import net.kitpvp.stats.bson.codec.BsonDecoder;
import net.kitpvp.stats.mongodb.api.async.AsyncTask;
import net.kitpvp.stats.mongodb.cursor.IterableCursor;
import net.kitpvp.stats.mongodb.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.mongodb.assertions.Assertions.isTrue;
import static com.mongodb.assertions.Assertions.notNull;


public final class MongoFindQuery extends AbstractMongoQuery implements AsyncTask, Iterable<StatsReader> {

    private static final Document EMPTY = new Document();

    private final Database database;
    private final Collection collection;
    private Bson filter = EMPTY, sort = EMPTY, projection;
    private int limit, skip;

    public MongoFindQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.limit = this.skip = 0;
    }

    public final MongoFindQuery filter(@NotNull Bson filter) {
        notNull("filter", filter);
        isTrue("filter already set", this.filter == EMPTY);
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

    public final MongoFindQuery projection(Bson projection) {
        notNull("projection", projection);
        isTrue("projection already set", this.projection == null);
        this.projection = projection;
        return this;
    }

    public final <T> @NotNull IterableCursor<T> findAndMap(BsonDecoder<T> decoder) {
        return this.query().map(decoder::decode);
    }

    public final <T> void findAndMapAsync(BsonDecoder<T> decoder, Consumer<IterableCursor<T>> callback) {
        this.findAndMapAsync(decoder, callback, Executors.DIRECT);
    }

    public final <T> void findAndMapAsync(BsonDecoder<T> decoder, Consumer<IterableCursor<T>> callback, Executor executor) {
        this.executeTaskAsync(this::findAndMap, decoder, callback, executor);
    }

    public final @NotNull IterableCursor<StatsReader> find() {
        Log.debug("Executing find query for {0} (Limit: {1}, Skip: {2}, Sort: {3})", this.filter, this.limit, this.skip, this.sort);

        return this.query();
    }

    public final void findAsync(Consumer<IterableCursor<StatsReader>> callback) {
        this.findAsync(callback, Executors.DIRECT);
    }

    public final void findAsync(Consumer<IterableCursor<StatsReader>> callback, Executor executor) {
        this.executeTaskAsync(this::find, callback, executor);
    }

    public final @Nullable StatsReader first() {
        return this.query().first();
    }

    public final @NotNull StatsReader firstOrElse(StatsReader statsReader) {
        StatsReader query;
        return (query = first()) != null ? query : statsReader;
    }

    public final @NotNull StatsReader firstOrElseGet(Supplier<StatsReader> supplier) {
        StatsReader query;
        return (query = first()) != null ? query : supplier.get();
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

    private IterableCursor<StatsReader> query() {
        Stats.checkForMainThread();

        try (AbstractMongoQuery ignored = this) {
            MongoIterable<StatsReader> iterable = this.database.getCollection(this.collection)
                    .find(this.filter)
                    .limit(this.limit)
                    .skip(this.skip)
                    .sort(this.sort)
                    .projection(this.projection)
                    .map(BsonStatsReader::new);

            return new IterableCursor<>(iterable);
        }
    }
}
