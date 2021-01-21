package net.kitpvp.stats.mongodb.query;

import com.google.common.base.Preconditions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.api.function.BooleanBiConsumer;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.api.function.BooleanConsumer;
import net.kitpvp.stats.mongodb.api.async.AsyncExecutable;
import net.kitpvp.stats.query.WriteQuery;
import net.kitpvp.stats.query.filter.Filter;
import net.kitpvp.stats.query.update.Update;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class MongoWriteQuery implements WriteQuery<MongoStatsReader>, AsyncExecutable {

    public static final boolean QUERY_UPDATE_MANY = false;
    public static final boolean QUERY_CHECK_MAIN_THREAD = true;

    private final Database database;
    private final Collection collection;
    private final MongoStatsReader criteria, update;
    private final Consumer<MongoStatsReader> callback;

    public MongoWriteQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.criteria = new MongoStatsReader(new Document());
        this.update = new MongoStatsReader(new Document());
        this.callback = null;
    }

    public MongoWriteQuery(Database database, Collection collection, Consumer<MongoStatsReader> callback) {
        this.database = database;
        this.collection = collection;
        this.criteria = new MongoStatsReader(new Document());
        this.update = new MongoStatsReader(new Document());
        this.callback = callback;
    }

    @Override
    @SafeVarargs
    public final MongoWriteQuery filter(@NotNull Filter<MongoStatsReader>... filters) {
        Preconditions.checkArgument(filters.length > 0, "Zero filters specified");
        Stream.of(filters).forEach(filter -> filter.append(this.criteria));
        return this;
    }

    @Override
    @SafeVarargs
    public final MongoWriteQuery update(@NotNull Update<MongoStatsReader>... updates) {
        Preconditions.checkArgument(updates.length > 0, "Zero updates specified");
        Stream.of(updates).forEach(update -> update.append(this.update));
        return this;
    }

    public final int updateSize() {
        return this.update.source().size();
    }

    public final int filterSize() {
        return this.criteria.source().size();
    }

    @Override
    public final void execute() {
        this.execute(QUERY_UPDATE_MANY);
    }

    public final void executeAsync(boolean updateMany) {
        this.executeTaskAsync((BooleanConsumer) this::execute, updateMany, null, null);
    }

    public final void execute(boolean updateMany) {
        this.execute(updateMany, QUERY_CHECK_MAIN_THREAD);
    }

    public final void executeAsync(boolean updateMany, boolean checkMainThread) {
        this.executeTaskAsync(this::execute, updateMany, checkMainThread, null, null);
    }

    public final void execute(boolean updateMany, boolean checkMainThread) {
        if(checkMainThread)
            Stats.checkForMainThread();

        if(this.update.source().isEmpty() || this.criteria.source().isEmpty())
            throw new UnsupportedOperationException("Empty criteria and/or update document");

        Log.debug("Executing update {0} for {1}", this.update.source(), this.criteria.source());
        if(updateMany)
            this.database.getCollection(this.collection).updateMany(this.criteria.source(), this.update.source(), new UpdateOptions().upsert(true));
        else
            this.database.getCollection(this.collection).updateOne(this.criteria.source(), this.update.source(), new UpdateOptions().upsert(true));

        this.cleanup();
    }

    public final MongoStatsReader executeAndFind() {
        return this.execute(ReturnDocument.AFTER);
    }

    public final void executeAndFindAsync() {
        this.executeAndFindAsync(null, null);
    }

    public final void executeAndFindAsync(Consumer<MongoStatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::executeAndFind, callback, executor);
    }

    public final MongoStatsReader findAndExecute() {
        return this.execute(ReturnDocument.BEFORE);
    }

    public final void findAndExecuteAsync() {
        this.findAndExecuteAsync(null, null);
    }

    public final void findAndExecuteAsync(Consumer<MongoStatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::findAndExecute, callback, executor);
    }

    private MongoStatsReader execute(ReturnDocument returnDocument) {
        Stats.checkForMainThread();

        Log.debug("Executing update {0} for {1} and returning {2}", this.update.source(), this.criteria.source(), returnDocument);
        if(this.update.source().isEmpty() || this.criteria.source().isEmpty())
            throw new UnsupportedOperationException("Empty criteria and/or update document");

        return this.giveBack(new MongoStatsReader(this.database.getCollection(this.collection).findOneAndUpdate(this.criteria.source(), this.update.source(), new FindOneAndUpdateOptions().returnDocument(returnDocument).upsert(true))));
    }

    private void cleanup() {
        this.update.source().clear();
        this.criteria.source().clear();
    }

    private MongoStatsReader giveBack(MongoStatsReader statsReader) {
        if(this.callback != null)
            this.callback.accept(statsReader);

        return statsReader;
    }
}
