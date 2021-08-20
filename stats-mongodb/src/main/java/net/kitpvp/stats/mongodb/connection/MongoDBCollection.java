package net.kitpvp.stats.mongodb.connection;

import com.mongodb.client.MongoCollection;
import net.kitpvp.stats.async.SyncExecutor;
import net.kitpvp.stats.mongodb.index.MongoIndex;
import net.kitpvp.stats.mongodb.query.*;
import org.bson.Document;

public interface MongoDBCollection {

    MongoCollection<Document> collection();

    SyncExecutor syncExecutor();

    default MongoWriteQuery write() {
        return new MongoWriteQuery(this);
    }

    default MongoInsertQuery insert() {
        return new MongoInsertQuery(this);
    }

    default MongoFindQuery find() {
        return new MongoFindQuery(this);
    }

    default MongoCountQuery count() {
        return new MongoCountQuery(this);
    }

    default MongoDeleteQuery delete() {
        return new MongoDeleteQuery(this);
    }

    default MongoAggregateQuery aggregate() {
        return new MongoAggregateQuery(this);
    }

    default MongoIndex index() {
        return new MongoIndex(this);
    }
}
