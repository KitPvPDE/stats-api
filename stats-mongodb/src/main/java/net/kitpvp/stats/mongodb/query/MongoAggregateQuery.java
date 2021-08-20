package net.kitpvp.stats.mongodb.query;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.cursor.IterableCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.kitpvp.stats.async.SyncExecutor.DIRECT;
import static org.bson.assertions.Assertions.notNull;

public class MongoAggregateQuery extends AbstractMongoQuery implements Iterable<StatsReader> {

    private final List<Bson> pipeline;

    public MongoAggregateQuery(MongoDBCollection collection) {
        super(collection);
        this.pipeline = new LinkedList<>();
    }

    public MongoAggregateQuery(MongoCollection<Document> mongoCollection) {
        super(mongoCollection);
        this.pipeline = new LinkedList<>();
    }

    public MongoAggregateQuery pipeline(@NotNull Bson element) {
        notNull("element", element);
        this.pipeline.add(element);
        return this;
    }

    public MongoAggregateQuery pipeline(@NotNull Bson... elements) {
        notNull("elements", elements);
        this.pipeline.addAll(Arrays.asList(elements));
        return this;
    }

    public MongoAggregateQuery pipeline(@NotNull Collection<? extends Bson> elements) {
        notNull("elements", elements);
        this.pipeline.addAll(elements);
        return this;
    }

    @NotNull
    public IterableCursor<StatsReader> aggregate() {
        return this.query();
    }

    public void aggregateAsync(Consumer<IterableCursor<StatsReader>> callback) {
        this.aggregateAsync(callback, DIRECT);
    }

    public void aggregateAsync(Consumer<IterableCursor<StatsReader>> callback, Executor callbackExecutor) {
        this.executeTaskAsync(this::aggregate, callback, callbackExecutor);
    }

    @Nullable
    public StatsReader first() {
        return this.query().first();
    }

    public void firstAsync(Consumer<StatsReader> callback) {
        this.firstAsync(callback, DIRECT);
    }

    public void firstAsync(Consumer<StatsReader> callback, Executor callbackExecutor) {
        this.executeTaskAsync(this::first, callback, callbackExecutor);
    }

    @NotNull
    public StatsReader firstOrElse(StatsReader statsReader) {
        StatsReader first;
        return (first = first()) == null ? statsReader : first;
    }

    @NotNull
    public StatsReader firstOrElseGet(Supplier<StatsReader> supplier) {
        StatsReader first;
        return (first = first()) == null ? supplier.get() : first;
    }

    @NotNull
    @Override
    public MongoCursor<StatsReader> iterator() {
        return this.query().cursor();
    }

    private IterableCursor<StatsReader> query() {
        this.checkForMainThread();

        try(MongoAggregateQuery ignored = this) {
            MongoIterable<StatsReader> mongoIterable = this.getMongoCollection()
                    .aggregate(this.pipeline)
                    .map(BsonStatsReader::new);

            return new IterableCursor<>(mongoIterable);
        }
    }
}
