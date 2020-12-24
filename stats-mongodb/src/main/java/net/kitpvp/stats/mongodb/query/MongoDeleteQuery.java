package net.kitpvp.stats.mongodb.query;

import com.google.common.base.Preconditions;
import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.api.async.AsyncExecutable;
import net.kitpvp.stats.query.DeleteQuery;
import net.kitpvp.stats.query.filter.Filter;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.stream.Stream;

public class MongoDeleteQuery implements DeleteQuery<MongoStatsReader>, AsyncExecutable {

    private final Database database;
    private final Collection collection;
    private final MongoStatsReader criteria;
    private final Consumer<MongoStatsReader> callback;

    public MongoDeleteQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.criteria = new MongoStatsReader(new Document());
        this.callback = null;
    }

    public MongoDeleteQuery(Database database, Collection collection, Consumer<MongoStatsReader> callback) {
        this.database = database;
        this.collection = collection;
        this.criteria = new MongoStatsReader(new Document());
        this.callback = callback;
    }


    @Override
    @SafeVarargs
    public final MongoDeleteQuery filter(@NotNull Filter<MongoStatsReader>... filters) {
        Preconditions.checkArgument(filters.length > 0, "Zero filters specified");
        Stream.of(filters).forEach(filter -> filter.append(this.criteria));
        return this;
    }

    @Override
    public void execute() {
        this.delete();
    }

    public final long delete() {
        return this.delete(false);
    }

    public final long delete(boolean deleteMany) {
        Stats.checkForMainThread();

        if(this.criteria.bson().isEmpty())
            throw new UnsupportedOperationException("Empty criteria and/or update document");

        Log.debug("Executing delete for {0}", this.criteria.bson());
        try{
            if(deleteMany){
                return this.database.getCollection(this.collection).deleteMany(this.criteria.bson()).getDeletedCount();
            }else{
                return this.database.getCollection(this.collection).deleteOne(this.criteria.bson()).getDeletedCount();
            }
        }finally{
            this.cleanup();
        }
    }

    public final void deleteAsync(LongConsumer callback) {
        this.deleteAsync(callback, Executors.DIRECT);
    }

    public final void deleteAsync(LongConsumer callback, Executor executor) {
        this.deleteAsync(callback, executor, false);
    }

    public final void deleteAsync(LongConsumer callback, Executor executor, boolean deleteMany) {
        this.executeTaskAsync(() -> {
            long deleted = this.delete(deleteMany);

            this.executeCallback(callback, deleted, executor);
        });
    }

    public final BsonStatsReader findAndDelete() {
        Stats.checkForMainThread();

        if(this.criteria.bson().isEmpty())
            throw new UnsupportedOperationException("Empty criteria and/or update document");

        Log.debug("Executing findAndDelete for {0}", this.criteria.bson());
        try{
            Document document = this.database.getCollection(this.collection).findOneAndDelete(this.criteria.bson());
            if(document != null)
                return this.giveBack(new MongoStatsReader(document));

            return null;
        }finally{
            this.cleanup();
        }
    }

    public final void findAndDeleteAsync(Consumer<BsonStatsReader> callback) {
        this.findAndDeleteAsync(callback, Executors.DIRECT);
    }

    public final void findAndDeleteAsync(Consumer<BsonStatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::findAndDelete, callback, executor);
    }

    private void cleanup() {
        this.criteria.bson().clear();
    }

    private MongoStatsReader giveBack(MongoStatsReader statsReader) {
        if(this.callback != null)
            this.callback.accept(statsReader);

        return statsReader;
    }
}
