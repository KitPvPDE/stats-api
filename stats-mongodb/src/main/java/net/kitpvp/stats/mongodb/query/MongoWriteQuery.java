package net.kitpvp.stats.mongodb.query;

import com.mongodb.client.model.*;
import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.function.BooleanConsumer;
import net.kitpvp.stats.mongodb.api.async.AsyncExecutable;
import net.kitpvp.stats.mongodb.model.Filters;
import net.kitpvp.stats.mongodb.model.Updates;
import net.kitpvp.stats.mongodb.query.bulk.MongoBulkOperation;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

import static com.mongodb.assertions.Assertions.notNull;
import static com.mongodb.assertions.Assertions.isTrue;

public final class MongoWriteQuery extends AbstractMongoQuery implements AsyncExecutable, MongoBulkOperation {

    public static final boolean QUERY_UPDATE_MANY = false;
    public static final boolean QUERY_CHECK_MAIN_THREAD = true;
    public static final boolean QUERY_UPSERT = true;

    private final Database database;
    private final Collection collection;
    private final List<Bson> updates;
    private boolean updateMany = QUERY_UPDATE_MANY, upsert = QUERY_UPDATE_MANY;
    private Bson filter = null;

    public MongoWriteQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.updates = new LinkedList<>();
    }

    public final MongoWriteQuery filter(@NotNull Bson filter) {
        this.filter = filter;
        return this;
    }

    public final MongoWriteQuery filters(@NotNull Bson... filters) {
        notNull("filters", filters);
        return this.filter(Filters.and(filters));
    }

    public final MongoWriteQuery update(@NotNull Bson update) {
        notNull("update", update);
        this.updates.add(update);
        return this;
    }

    public final MongoWriteQuery update(@NotNull Bson... updates) {
        return this.update(Arrays.asList(updates));
    }

    public final MongoWriteQuery update(@NotNull Iterable<Bson> updates) {
        notNull("updates", updates);
        for(Bson update : updates) {
            notNull("update", update);
            this.updates.add(update);
        }
        return this;
    }

    public final MongoWriteQuery update(@NotNull Bson[]... updates) {
        notNull("updates", updates);

        for(Bson[] update : updates) {
            notNull("update", update);
            this.update(update);
        }
        return this;
    }

    public final MongoWriteQuery updateMany(boolean updateMany) {
        this.updateMany = updateMany;
        return this;
    }

    public final MongoWriteQuery upsert(boolean upsert) {
        this.upsert = upsert;
        return this;
    }

    public final int updates() {
        return this.updates.size();
    }

    public final int filters() {
        return 1;
    }

    @Override
    public final void execute() {
        this.execute(updateMany);
    }

    public final void execute(boolean updateMany) {
        this.execute(updateMany, QUERY_CHECK_MAIN_THREAD);
    }

    public final void execute(boolean updateMany, boolean checkMainThread) {
        this.execute(updateMany, checkMainThread, upsert);
    }

    public final void execute(boolean updateMany, boolean checkMainThread, boolean upsert) {
        this.checkQuery(updateMany, checkMainThread);
        try (AbstractMongoQuery ignored = this) {
            if(updateMany) {
                this.database.getCollection(this.collection).updateMany(this.filter, Updates.combine(this.updates), new UpdateOptions().upsert(upsert));
            } else {
                this.database.getCollection(this.collection).updateOne(this.filter, Updates.combine(this.updates), new UpdateOptions().upsert(upsert));
            }
        }
    }

    @Override
    public void executeAsync() {
        this.executeTaskAsync(this::execute, (Runnable) null, null);
    }

    public void executeAsync(Runnable callback) {
        this.executeAsync(callback, Executors.DIRECT);
    }

    public void executeAsync(Runnable callback, Executor executor) {
        this.executeTaskAsync(this::execute, callback, executor);
    }

    public final void executeAsync(boolean updateMany) {
        this.executeTaskAsync((BooleanConsumer) this::execute, updateMany, null, null);
    }

    public final void executeAsync(boolean updateMany, boolean checkMainThread, boolean upsert) {
        this.executeTaskAsync(this::execute, updateMany, checkMainThread, upsert, null, null);
    }

    @NotNull
    public final StatsReader executeAndFind() {
        return this.executeAndFind(QUERY_CHECK_MAIN_THREAD, QUERY_UPSERT);
    }

    @Contract("_,true -> !null")
    public final StatsReader executeAndFind(boolean checkMainThread, boolean upsert) {
        return this.execute(ReturnDocument.AFTER, checkMainThread, upsert);
    }

    public final void executeAndFindAsync(Consumer<StatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::executeAndFind, callback, executor);
    }

    public final @Nullable StatsReader findAndExecute() {
        return this.findAndExecute(QUERY_CHECK_MAIN_THREAD, QUERY_UPSERT);
    }

    public final @Nullable StatsReader findAndExecute(boolean checkMainThread, boolean upsert) {
        return this.execute(ReturnDocument.BEFORE, checkMainThread, upsert);
    }

    public final void findAndExecuteAsync() {
        this.findAndExecuteAsync(null, null);
    }

    public final void findAndExecuteAsync(Consumer<StatsReader> callback, Executor executor) {
        this.executeTaskAsync(this::findAndExecute, callback, executor);
    }

    @Override
    public WriteModel<? extends Document> model() {
        if(this.updateMany) {
            return new UpdateManyModel<>(this.filter, Updates.combine(this.updates), new UpdateOptions().upsert(upsert));
        } else {
            return new UpdateOneModel<>(this.filter, Updates.combine(this.updates), new UpdateOptions().upsert(upsert));
        }
    }

    private @Nullable StatsReader execute(ReturnDocument returnDocument, boolean checkMainThread, boolean upsert) {
        this.checkQuery(false, checkMainThread);

        try (AbstractMongoQuery ignored = this) {
            Document document = this.database.getCollection(this.collection)
                    .findOneAndUpdate(this.filter, Updates.combine(this.updates), new FindOneAndUpdateOptions().returnDocument(returnDocument).upsert(upsert));
            if(document != null) {
                return new BsonStatsReader(document);
            }
            return null;
        }
    }

    private void checkQuery(boolean updateMany, boolean checkMainThread) {
        if(checkMainThread)
            Stats.checkForMainThread();

        isTrue("Empty update document", this.updates.size() > 0);
        isTrue("Empty filter document", !updateMany || this.filter != null);

        Log.debug("Executing update {0} for {1}", this.updates, this.filter);
    }
}
