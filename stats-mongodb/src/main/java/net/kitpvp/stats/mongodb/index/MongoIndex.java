package net.kitpvp.stats.mongodb.index;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.function.Consumer;

public class MongoIndex {

    private final MongoDBCollection collection;
    private IndexOptions indexOptions = new IndexOptions();
    private Bson bson;

    public MongoIndex(MongoDBCollection collection) {
        this.collection = collection;
    }

    public MongoIndex fields(Bson bson) {
        this.bson = bson;
        return this;
    }

    public MongoIndex options(Consumer<IndexOptions> block) {
        block.accept(this.indexOptions);
        return this;
    }

    public void create() {
        this.getMongoCollection().createIndex(this.bson, this.indexOptions);
    }

    private MongoCollection<Document> getMongoCollection() {
        return this.collection.collection();
    }
}
