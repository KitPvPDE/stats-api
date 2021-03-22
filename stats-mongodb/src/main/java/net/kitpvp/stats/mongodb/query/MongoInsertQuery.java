package net.kitpvp.stats.mongodb.query;

import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.mongodb.api.async.AsyncExecutable;
import net.kitpvp.stats.mongodb.query.bulk.MongoBulkOperation;
import org.bson.Document;

import java.util.stream.Stream;

import static com.mongodb.assertions.Assertions.notNull;

public final class MongoInsertQuery implements AsyncExecutable, MongoBulkOperation {

    private final Database database;
    private final Collection collection;
    private final BsonStatsWriter document;

    public MongoInsertQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.document = new BsonStatsWriter();
    }

    public final MongoInsertQuery insert(Document... inserts) {
        notNull("inserts", inserts);
        Stream.of(inserts).forEach(document -> this.document.bson().putAll(document));
        return this;
    }

    public final void execute() {
        Stats.checkForMainThread();

        this.database.getCollection(this.collection).insertOne(this.document.bson());
    }

    @Override
    public final WriteModel<? extends Document> model() {
        return new InsertOneModel<>(this.document.bson());
    }
}
