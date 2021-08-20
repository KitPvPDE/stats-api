package net.kitpvp.stats.mongodb.query;

import com.mongodb.client.MongoCollection;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.async.AsyncExecutable;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

import static com.mongodb.assertions.Assertions.notNull;
import static net.kitpvp.stats.async.SyncExecutor.DIRECT;

public final class MongoDeleteQuery extends AbstractMongoQuery implements AsyncExecutable {

    public static final boolean QUERY_DELETE_MANY = false;
    public static final boolean QUERY_CHECK_MAIN_THREAD = true;

    private Bson filter;

    public MongoDeleteQuery(MongoDBCollection collection) {
        super(collection);
    }

    public MongoDeleteQuery(MongoCollection<Document> collection) {
        super(collection);
    }

    public final MongoDeleteQuery filter(@NotNull Bson filter) {
        notNull("filter", filter);
        this.filter = filter;
        return this;
    }

    public final MongoDeleteQuery filters(@NotNull Bson... filters) {
        notNull("filters", filters);
        return this.filter(Filters.and(filters));
    }

    @Override
    public void execute() {
        this.delete();
    }

    public final long delete() {
        return this.delete(QUERY_DELETE_MANY);
    }

    public final long delete(boolean deleteMany) {
        return this.delete(QUERY_CHECK_MAIN_THREAD, deleteMany);
    }

    public final long delete(boolean checkMainThread, boolean deleteMany) {
        this.checkQuery(checkMainThread, deleteMany);

        try (AbstractMongoQuery ignored = this) {
            if (deleteMany) {
                return this.getMongoCollection().deleteMany(this.filter).getDeletedCount();
            } else {
                return this.getMongoCollection().deleteOne(this.filter).getDeletedCount();
            }
        }
    }

    public final void deleteAsync(LongConsumer callback) {
        this.deleteAsync(callback, DIRECT);
    }

    public final void deleteAsync(LongConsumer callback, Executor executor) {
        this.deleteAsync(callback, executor, false);
    }

    public final void deleteAsync(LongConsumer callback, Executor executor, boolean deleteMany) {
        this.executeTaskAsync(() -> this.delete(deleteMany), callback, executor);
    }

    public final StatsReader findAndDelete() {
        return this.findAndDelete(QUERY_CHECK_MAIN_THREAD);
    }

    public final BsonStatsReader findAndDelete(boolean checkMainThread) {
        this.checkQuery(checkMainThread, false);

        try (AbstractMongoQuery ignored = this) {
            Document document = this.getMongoCollection().findOneAndDelete(this.filter);
            if (document != null) {
                return new BsonStatsReader(document);
            }
            return null;
        }
    }

    public final void findAndDeleteAsync(Consumer<StatsReader> callback) {
        this.findAndDeleteAsync(callback, DIRECT);
    }

    public final void findAndDeleteAsync(Consumer<StatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::findAndDelete, callback, executor);
    }

    private void checkQuery(boolean checkMainThread, boolean deleteMany) {
        if (checkMainThread)
            this.checkForMainThread();

        notNull("filter cannot be null", this.filter);
    }
}
