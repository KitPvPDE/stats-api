package net.kitpvp.stats.mongodb.query;

import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.mongodb.api.async.AsyncExecutable;
import net.kitpvp.stats.mongodb.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

import static com.mongodb.assertions.Assertions.notNull;

public final class MongoDeleteQuery implements AsyncExecutable {

    public static final boolean QUERY_DELETE_MANY = false;
    public static final boolean QUERY_CHECK_MAIN_THREAD = true;

    private final Database database;
    private final Collection collection;
    private Bson filter;

    public MongoDeleteQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
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

        Log.debug("Executing delete for {0}", this.filter);
        if (deleteMany) {
            return this.database.getCollection(this.collection).deleteMany(this.filter).getDeletedCount();
        } else {
            return this.database.getCollection(this.collection).deleteOne(this.filter).getDeletedCount();
        }
    }

    public final void deleteAsync(LongConsumer callback) {
        this.deleteAsync(callback, Executors.DIRECT);
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

        Log.debug("Executing findAndDelete for {0}", this.filter);
        Document document = this.database.getCollection(this.collection).findOneAndDelete(this.filter);
        if (document != null) {
            return new BsonStatsReader(document);
        }
        return null;
    }

    public final void findAndDeleteAsync(Consumer<StatsReader> callback) {
        this.findAndDeleteAsync(callback, Executors.DIRECT);
    }

    public final void findAndDeleteAsync(Consumer<StatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::findAndDelete, callback, executor);
    }

    private void checkQuery(boolean checkMainThread, boolean deleteMany) {
        if (checkMainThread)
            Stats.checkForMainThread();

        notNull("filter cannot be null", this.filter);
    }
}
