package net.kitpvp.stats.mongodb.query;

import com.google.common.base.Preconditions;
import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.api.async.AsyncTask;
import net.kitpvp.stats.query.CountQuery;
import net.kitpvp.stats.query.filter.Filter;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.stream.Stream;

public final class MongoCountQuery implements CountQuery<MongoStatsReader>, AsyncTask {

    private final Database database;
    private final Collection collection;
    private final MongoStatsReader query;

    public MongoCountQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.query = new MongoStatsReader(new Document());
    }

    @SafeVarargs
    public final MongoCountQuery filter(@NotNull Filter<MongoStatsReader> ... filters) {
        Preconditions.checkArgument(filters.length > 0, "Zero filters specified");
        Stream.of(filters).forEach(filter -> filter.append(this.query));
        return this;
    }

    public final long count() {
        Stats.checkForMainThread();

        Log.debug("Executing count for {0}", this.query.source());
        return this.database.getCollection(this.collection).countDocuments(this.query.source());
    }

    public final void countAsync(LongConsumer callback) {
        this.countAsync(callback, Executors.DIRECT);
    }

    public final void countAsync(LongConsumer callback, Executor executor) {
        this.executeTaskAsync(this::count, callback, executor);
    }
}
