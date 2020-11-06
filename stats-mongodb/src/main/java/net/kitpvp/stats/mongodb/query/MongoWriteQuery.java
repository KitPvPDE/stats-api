package net.kitpvp.stats.mongodb.query;

import com.google.common.base.Preconditions;
import com.mongodb.client.model.UpdateOptions;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.api.async.AsyncExecutable;
import net.kitpvp.stats.query.WriteQuery;
import net.kitpvp.stats.query.filter.Filter;
import net.kitpvp.stats.query.filter.Update;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public final class
MongoWriteQuery implements WriteQuery<MongoStatsReader>, AsyncExecutable {

    private final Database database;
    private final Collection collection;
    private final MongoStatsReader criteria, update;

    public MongoWriteQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.criteria = new MongoStatsReader(new Document());
        this.update = new MongoStatsReader(new Document());
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

    @Override
    public final void execute() {
        this.execute(false);
    }

    public final void execute(boolean updateMany) {
        if(this.update.source().isEmpty() || this.criteria.source().isEmpty())
            throw new UnsupportedOperationException("Empty criteria and/or update document");

        if(updateMany)
            this.database.getCollection(this.collection).updateMany(this.criteria.source(), this.update.source(), new UpdateOptions().upsert(true));
        else
            this.database.getCollection(this.collection).updateOne(this.criteria.source(), this.update.source(), new UpdateOptions().upsert(true));
    }
}
