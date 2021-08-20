package net.kitpvp.stats.mongodb.query;

import com.mongodb.client.MongoCollection;
import net.kitpvp.stats.async.AsyncTask;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.function.LongConsumer;

import static com.mongodb.assertions.Assertions.notNull;
import static net.kitpvp.stats.async.SyncExecutor.DIRECT;

public final class MongoCountQuery extends AbstractMongoQuery {

    private @Nullable Bson filter;

    public MongoCountQuery(MongoDBCollection collection) {
        super(collection);
    }

    public MongoCountQuery(MongoCollection<Document> collection) {
        super(collection);
    }

    public final MongoCountQuery filter(@NotNull Bson bson) {
        notNull("filter", bson);
        this.filter = bson;
        return this;
    }

    public final MongoCountQuery filters(@NotNull Bson... filters) {
        notNull("filters", filters);
        return this.filter(Filters.and(filters));
    }

    public final long count() {
        this.checkForMainThread();

        try (AbstractMongoQuery ignored = this) {
            if (this.filter == null) {
                return this.getMongoCollection().countDocuments();
            }
            return this.getMongoCollection().countDocuments(this.filter);
        }
    }

    public final void countAsync(LongConsumer callback) {
        this.countAsync(callback, DIRECT);
    }

    public final void countAsync(LongConsumer callback, Executor executor) {
        this.executeTaskAsync(this::count, callback, executor);
    }
}
