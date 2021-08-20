package net.kitpvp.stats.mongodb.query;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.WriteModel;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.function.BooleanConsumer;
import net.kitpvp.stats.async.AsyncExecutable;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.query.bulk.MongoBulkOperation;
import org.bson.Document;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

@RequiredArgsConstructor
public final class MongoBulkQuery extends AbstractMongoQuery implements AsyncExecutable {

    public static final boolean QUERY_ORDERED = true;

    private final List<WriteModel<? extends Document>> models;

    public MongoBulkQuery(MongoDBCollection collection) {
        super(collection);
        this.models = new CopyOnWriteArrayList<>();
    }

    public MongoBulkQuery(MongoCollection<Document> collection) {
        super(collection);
        this.models = new CopyOnWriteArrayList<>();
    }

    public final MongoBulkQuery query(MongoBulkOperation... operations) {
        Stream.of(operations).map(MongoBulkOperation::model).forEach(this.models::add);
        return this;
    }

    @Override
    public final void execute() {
        this.execute(QUERY_ORDERED);
    }

    public final void execute(boolean ordered) {
        try (AbstractMongoQuery ignored = this) {
            this.getMongoCollection().bulkWrite(this.models, new BulkWriteOptions().ordered(ordered));
        }
    }

    public final void executeAsync(boolean ordered) {
        this.executeTaskAsync((BooleanConsumer) this::execute, ordered, null, null);
    }
}
