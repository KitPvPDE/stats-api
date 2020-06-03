package net.kitpvp.pluginapi.modules.stats.ranking;

import net.kitpvp.pluginapi.modules.stats.StatsReader;
import net.kitpvp.pluginapi.modules.stats.mongo.MongoStats;
import net.kitpvp.pluginapi.modules.stats.mongo.MongoStatsReader;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;
import org.bson.Document;

public class Ranking {

    public static <K, V extends Number> long calculateRanking(MongoStats stats, StatsReader statsReader, StatsKey<K, V> statsKey, K k) {
        String key = statsKey.getKey(k);
        long value = statsReader.getStatKey(statsKey, k).longValue();
        long before = stats.getStatsCollection().getCollection().count(new Document(key, new Document("$gt", value)));

        return before + 1;
    }

    public static <K, V extends Number> long calculateRanking(MongoStats stats, StatsReader statsReader, SeasonKey<K, V> statsKey, K k, int season) {
        StatsKey<K, V> statKey = statsKey.season(season);
        String key = statKey.getKey(k);
        long value = statsReader.getStatKey(statKey, k).longValue();
        long before = stats.getStatsCollection().getCollection().count(new Document(key, new Document("$gt", value)));

        return before + 1;
    }

    public static <V extends Number> long calculateRanking(MongoStats stats, StatsReader statsReader, SSeasonKey<V> statsKey, int season) {
        SStatsKey<V> statKey = statsKey.season(season);
        String key = statKey.getKey();
        long value = statsReader.getStatKey(statKey).longValue();
        long before = stats.getStatsCollection().getCollection().count(new Document(key, new Document("$gt", value)));

        return before + 1;
    }

}
