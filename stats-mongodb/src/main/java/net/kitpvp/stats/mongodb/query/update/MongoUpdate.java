package net.kitpvp.stats.mongodb.query.update;

import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.query.filter.Update;
import org.bson.Document;

@FunctionalInterface
public interface MongoUpdate extends Update<MongoStatsReader> {

    @Override
    MongoUpdate append(MongoStatsReader document);

    default Document document(Document document, net.kitpvp.stats.mongodb.api.Operator operator) {
        Document operatorDocument = document.get(operator.command(), Document.class);
        if(operatorDocument == null) {
            document.put(operator.command(), operatorDocument = new Document());
        }
        return operatorDocument;
    }
}
