package net.kitpvp.stats.mongodb.query;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import net.kitpvp.stats.async.AsyncExecutable;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.query.bulk.MongoBulkOperation;
import org.bson.Document;

import java.util.stream.Stream;

import static com.mongodb.assertions.Assertions.notNull;

public final class MongoInsertQuery extends AbstractMongoQuery implements AsyncExecutable, MongoBulkOperation {

    private final Document document;

    public MongoInsertQuery(MongoDBCollection collection) {
        super(collection);
        this.document = new Document();
    }

    public MongoInsertQuery(MongoCollection<Document> collection) {
        super(collection);
        this.document = new Document();
    }

    public final MongoInsertQuery insert(Document... inserts) {
        notNull("inserts", inserts);
        Stream.of(inserts).forEach(this.document::putAll);
        return this;
    }

    public final void execute() {
        this.checkForMainThread();

        try (AbstractMongoQuery ignored = this) {
            this.getMongoCollection().insertOne(this.document);
        }
    }

    @Override
    public final WriteModel<? extends Document> model() {
        return new InsertOneModel<>(this.document);
    }
}
