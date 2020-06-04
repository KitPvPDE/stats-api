package net.kitpvp.pluginapi.modules.stats.mongo;

import com.mongodb.client.MongoIterable;
import net.kitpvp.mongodbapi.MongoCollection;
import net.kitpvp.pluginapi.modules.stats.mongo.find.Comparison;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import org.bson.Document;

public class Mongo {

    public static <V> MongoStatsReader findOne(MongoCollection collection, SStatsKey<V> statsKey, V v) {
        return findOne(collection, statsKey, Comparison.EQUALS, v);
    }

    public static <V> MongoStatsReader findOne(MongoCollection collection, SStatsKey<V> statsKey, Comparison comparison, V v) {
        return findOne(collection, statsKey, comparison, null, v);
    }

    public static <K, V> MongoStatsReader findOne(MongoCollection collection, StatsKey<K, V> statsKey, K k, V v) {
        return findOne(collection, statsKey, Comparison.EQUALS, k, v);
    }

    public static <K, V> MongoStatsReader findOne(MongoCollection collection, StatsKey<K, V> statsKey, Comparison comparison, K k, V v) {
        return find(collection, statsKey, comparison, k, v).first();
    }

    public static <K, V> MongoIterable<MongoStatsReader> find(MongoCollection collection, StatsKey<K, V> statsKey, Comparison comparison, K k, V v) {
        String key = statsKey.getKey(k);

        Document document = new Document().append(key, new Document(comparison.getCommand(), v));
        if(document.isEmpty())
            throw new UnsupportedOperationException("Cannot iterate over all documents");

        return collection.getCollection().find().map(MongoStatsReader::new);
    }

}
