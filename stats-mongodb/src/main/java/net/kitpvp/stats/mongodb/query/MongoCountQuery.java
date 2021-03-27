package net.kitpvp.stats.mongodb.query;

import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.mongodb.api.async.AsyncTask;
import net.kitpvp.stats.mongodb.model.Filters;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.function.LongConsumer;

import static com.mongodb.assertions.Assertions.notNull;

public final class MongoCountQuery extends AbstractMongoQuery implements AsyncTask {

    private final Database database;
    private final Collection collection;
    private @Nullable Bson filter;

    public MongoCountQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
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
        Stats.checkForMainThread();

        Log.debug("Executing count for {0}", this.filter);
        try (AbstractMongoQuery ignored = this) {
            if(this.filter == null) {
                return this.database.getCollection(this.collection).countDocuments();
            }
            return this.database.getCollection(this.collection).countDocuments(this.filter);
        }
    }

    public final void countAsync(LongConsumer callback) {
        this.countAsync(callback, Executors.DIRECT);
    }

    public final void countAsync(LongConsumer callback, Executor executor) {
        this.executeTaskAsync(this::count, callback, executor);
    }
}
