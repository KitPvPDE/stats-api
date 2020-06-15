package net.kitpvp.pluginapi.modules.stats.mongo.queries;

import net.kitpvp.mongodbapi.MongoCollection;
import net.kitpvp.pluginapi.modules.stats.mongo.find.Comparison;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.write.WriteAction;
import org.bson.Document;

import java.util.*;

public class WriteQuery {

    private final MongoCollection collection;
    private final Document criteria, update;

    public WriteQuery(MongoCollection collection) {
        this.collection = collection;
        this.criteria = new Document();
        this.update = new Document();
    }

    public <V> WriteQuery filter(Comparison comparison, SStatsKey<V> statsKey, V v) {
        return this.filter(comparison, statsKey, null, v);
    }

    public <K, V> WriteQuery filter(Comparison comparison, StatsKey<K, V> statsKey, K k, V v) {
        this.criteria.append(statsKey.getKey(k), new Document("$" + comparison.getCommand(), v));
        return this;
    }

    public <V> WriteQuery update(WriteAction action, SStatsKey<V> statsKey, V v) {
        return this.update(action, statsKey, null, v);
    }

    public <K, V> WriteQuery update(WriteAction action, StatsKey<K, V> statsKey, K k, V v) {
        Document document = this.getOrCreate(action);

        if(action.getArrayCommand() != null) {
            if(!(v instanceof Collection)) {
                throw new UnsupportedOperationException("Cannot perform array actions on non-collection objects");
            }
            Collection<?> collection = (Collection<?>) v;
            Set<Object> list = new HashSet<>(collection);
            Document arrayDocument = (Document) document.computeIfAbsent(statsKey.getKey(k), (ignored) -> new Document());
            String arrayKey = "$" + action.getArrayCommand();
            if(arrayDocument.containsKey(arrayKey)) {
                List<?> old = arrayDocument.getList(arrayKey, Object.class);
                for(Object object : old) {
                    if(list.contains(object))
                        continue;

                    list.add(object);
                }
            }
            document.put(statsKey.getKey(k), new Document(arrayKey, list));
        } else {
            document.put(statsKey.getKey(k), v);
        }
        return this;
    }

    public void execute() {
        this.execute(false);
    }

    public void execute(boolean updateMany) {
        if(this.update.isEmpty() || this.criteria.isEmpty())
            throw new UnsupportedOperationException("Empty criteria and/or update document");

        if(updateMany)
            this.collection.getCollection().updateMany(this.criteria, this.update);
        else
            this.collection.getCollection().updateOne(this.criteria, this.update);
    }

    private Document getOrCreate(WriteAction action) {
        String command = "$" + action.getCommand();

        if(this.update.containsKey(command))
            return this.update.get(command, Document.class);

        Document document = new Document();
        this.update.put(command, document);

        return document;
    }
}
