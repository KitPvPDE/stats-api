package net.kitpvp.pluginapi.modules.stats.mongo.queries;

import net.kitpvp.mongodbapi.MongoCollection;
import net.kitpvp.pluginapi.modules.stats.mongo.find.Comparison;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import org.bson.Document;

public class CountQuery {

    private final MongoCollection collection;
    private final Document query;

    public CountQuery(MongoCollection collection) {
        this.collection = collection;
        this.query = new Document();
    }

    public final <K, V> CountQuery criteria(StatsKey<K, V> statsKey, K k, V v) {
        return this.criteria(statsKey, k, v, Comparison.EQUALS);
    }

    public final <K, V> CountQuery criteria(StatsKey<K, V> statsKey, K k, V v, Comparison comparison) {
        String key = statsKey.getKey(k);
        this.query.computeIfAbsent(key, (x) -> new Document());
        this.query.get(key, Document.class).append("$" + comparison.getCommand(), v);
        return this;
    }

    public final long count() {
        return this.collection.getCollection().countDocuments(this.query);
    }

}
