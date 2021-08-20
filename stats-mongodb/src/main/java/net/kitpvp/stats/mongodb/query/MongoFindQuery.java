package net.kitpvp.stats.mongodb.query;

import com.mongodb.Function;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.bson.codec.BsonDecoder;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.cursor.IterableCursor;
import net.kitpvp.stats.mongodb.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.mongodb.assertions.Assertions.isTrue;
import static com.mongodb.assertions.Assertions.notNull;
import static net.kitpvp.stats.async.SyncExecutor.DIRECT;


public final class MongoFindQuery extends AbstractMongoQuery implements Iterable<StatsReader> {

    private static final Document EMPTY = new Document();

    private Bson filter = EMPTY, sort = EMPTY, projection;
    private int limit, skip;

    public MongoFindQuery(MongoDBCollection collection) {
        super(collection);
        this.limit = this.skip = 0;
    }

    public MongoFindQuery(MongoCollection<Document> collection) {
        super(collection);
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
        this.findAndMapAsync(decoder, callback, DIRECT);
    }

    public final <T> void findAndMapAsync(BsonDecoder<T> decoder, Consumer<IterableCursor<T>> callback, Executor executor) {
        this.executeTaskAsync(this::findAndMap, decoder, callback, executor);
    }

    public final @NotNull IterableCursor<StatsReader> find() {
        return this.query();
    }

    public final void findAsync(Consumer<IterableCursor<StatsReader>> callback) {
        this.findAsync(callback, DIRECT);
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
        this.firstAsync(callback, DIRECT);
    }

    public final void firstAsync(Consumer<StatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::first, callback, executor);
    }

    public final @NotNull MongoCursor<StatsReader> iterator() {
        return this.query().cursor();
    }

    private IterableCursor<StatsReader> query() {
        this.checkForMainThread();

        try (AbstractMongoQuery ignored = this) {
            MongoIterable<StatsReader> iterable = this.getMongoCollection()
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
