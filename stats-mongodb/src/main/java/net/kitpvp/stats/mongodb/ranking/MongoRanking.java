package net.kitpvp.stats.mongodb.ranking;

import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.mongodb.Mongo;
import net.kitpvp.stats.mongodb.MongoStats;
import net.kitpvp.stats.mongodb.query.filter.filters.MongoFilters;

public class MongoRanking {

    public static <K, V> long calculateRanking(Database database, MongoStats stats, StatsReader statsReader, StatsKey<K, V> statsKey, K k) {
        return calculateRanking(database, stats.getCollection(), statsReader, statsKey, k);
    }

    public static <K, V> long calculateRanking(Database database, Collection collection, StatsReader statsReader, StatsKey<K, V> statsKey, K k) {
        String key = statsKey.key(k);
        V value = statsReader.getStatKey(statsKey, k);

        return Mongo.count(database, collection, MongoFilters.greater(statsKey, k, value)).
                count() + 1;
    }
}
