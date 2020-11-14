package net.kitpvp.stats.mongodb.query.update;

import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.query.update.Action;
import net.kitpvp.stats.query.update.ArrayAction;
import net.kitpvp.stats.query.update.Update;
import org.bson.Document;

@FunctionalInterface
public interface MongoUpdate extends Update<MongoStatsReader> {

    @Override
    MongoUpdate append(MongoStatsReader document);

    default Document document(Document document, Action operator) {
        return this.document(document, operator.getMongoOperator());
    }

    default Document document(Document document, ArrayAction operator) {
        return this.document(document, operator.getMongoOperator());
    }
}
