package net.kitpvp.stats.mongodb.ranking;

import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.keys.VoidStatsKey;
import net.kitpvp.stats.mongodb.Mongo;
import net.kitpvp.stats.mongodb.MongoStats;
import net.kitpvp.stats.mongodb.model.Filters;

public class MongoRanking {

    public static <V> long calculateRanking(Database database, MongoStats stats, StatsReader statsReader, VoidStatsKey<V> statsKey) {
        return calculateRanking(database, stats.getCollection(), statsReader, statsKey, null);
    }

    public static <K, V> long calculateRanking(Database database, MongoStats stats, StatsReader statsReader, StatsKey<K, V> statsKey, K k) {
        return calculateRanking(database, stats.getCollection(), statsReader, statsKey, k);
    }

    public static <V> long calculateRanking(Database database, Collection collection, StatsReader statsReader, VoidStatsKey<V> statsKey) {
        return calculateRanking(database, collection, statsReader, statsKey, null);
    }

    public static <K, V> long calculateRanking(Database database, Collection collection, StatsReader statsReader, StatsKey<K, V> statsKey, K k) {
        V value = statsReader.getStatKey(statsKey, k);

        return Mongo.count(database, collection, Filters.gt(statsKey, k, value)).
                count() + 1;
    }
}
