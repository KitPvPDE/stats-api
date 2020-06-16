package net.kitpvp.pluginapi.modules.stats.mongo;

import com.mongodb.client.MongoIterable;
import net.kitpvp.mongodbapi.MongoCollection;
import net.kitpvp.pluginapi.modules.stats.StatsReader;
import net.kitpvp.pluginapi.modules.stats.mongo.find.Comparison;
import net.kitpvp.pluginapi.modules.stats.mongo.queries.FindQuery;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import org.bson.Document;

public class Mongo {

    public static <V> StatsReader findOne(MongoCollection collection, SStatsKey<V> statsKey, V v) {
        return findOne(collection, statsKey, Comparison.EQUALS, v);
    }

    public static <V> StatsReader findOne(MongoCollection collection, SStatsKey<V> statsKey, Comparison comparison, V v) {
        return findOne(collection, statsKey, comparison, null, v);
    }

    public static <K, V> StatsReader findOne(MongoCollection collection, StatsKey<K, V> statsKey, K k, V v) {
        return findOne(collection, statsKey, Comparison.EQUALS, k, v);
    }

    public static <K, V> StatsReader findOne(MongoCollection collection, StatsKey<K, V> statsKey, Comparison comparison, K k, V v) {
        return find(collection, statsKey, comparison, k, v).first();
    }

    public static <V> MongoIterable<StatsReader> find(MongoCollection collection, SStatsKey<V> statsKey, Comparison comparison, V v) {
        return find(collection, statsKey, comparison, null, v);
    }

    public static <K, V> MongoIterable<StatsReader> find(MongoCollection collection, StatsKey<K, V> statsKey, Comparison comparison, K k, V v) {
        String key = statsKey.getKey(k);

        Document document = new Document().append(key, new Document("$" + comparison.getCommand(), v));
        if(document.isEmpty())
            throw new UnsupportedOperationException("Cannot iterate over all documents");

        return collection.getCollection().find(document).map(MongoStatsReader::new);
    }

    public static FindQuery find(MongoCollection collection) {
        return new FindQuery(MongoStatsReader::new, collection);
    }

    public static <V> FindQuery find(MongoCollection collection, SStatsKey<V> statsKey, V v, Comparison comparison) {
        return new FindQuery(MongoStatsReader::new, collection, statsKey, v, comparison);
    }

    public static <K, V> FindQuery find(MongoCollection collection, StatsKey<K, V> statsKey, K k, V v, Comparison comparison) {
        return new FindQuery(MongoStatsReader::new, collection, statsKey, k, v, comparison);
    }


}
