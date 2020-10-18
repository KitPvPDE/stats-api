package net.kitpvp.pluginapi.modules.stats.ranking;

import net.kitpvp.pluginapi.modules.stats.StatsReader;
import net.kitpvp.pluginapi.modules.stats.mongo.MongoStats;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;
import org.bson.Document;

public class Ranking {

    public static final int[] LEVELS = {
            125, 175, 225, 275, 350, 425, 525, 650, 650, 700,
            750, 800, 850, 900, 950, 1000, 1100, 1200, 1300, 1400,
            1500, 1600, 1700, 1800, 1900, 2000, 2150, 2300, 2450, 2600,
            2750, 2900, 3100, 3300, 3500, 3700, 3900, 4100, 4300, 4500,
            4700, 4900, 5100, 5300, 5500, 5700, 5900, 6100, 6300, 6500,
            6700, 6900, 7100, 7300, 7500, 7700, 7900, 8100, 8300, 8500,
            8700, 8900, 9200, 9400, 9600, 9800, 10000, 10250, 10500, 10750,
            11000, 11300, 11600, 12000, 12300, 12600, 13000, 13500, 14000, 14500,
            15000, 15500, 16000, 16500, 17000, 17500, 18000, 18500, 19000, 19500,
            20000, 21000, 22000, 23000, 24000, 25000, 26000, 27000, 28000, 30000};

    public static <K, V extends Number> long calculateRanking(MongoStats stats, StatsReader statsReader, StatsKey<K, V> statsKey, K k) {
        String key = statsKey.getKey(k);
        long value = statsReader.getStatKey(statsKey, k).longValue();
        long before = stats.getStatsCollection().getCollection().countDocuments(new Document(key, new Document("$gt", value)));

        return before + 1;
    }

    public static <K, V extends Number> long calculateRanking(MongoStats stats, StatsReader statsReader, SeasonKey<K, V> statsKey, K k, int season) {
        StatsKey<K, V> statKey = statsKey.season(season);
        String key = statKey.getKey(k);
        long value = statsReader.getStatKey(statKey, k).longValue();
        long before = stats.getStatsCollection().getCollection().countDocuments(new Document(key, new Document("$gt", value)));

        return before + 1;
    }

    public static <V extends Number> long calculateRanking(MongoStats stats, StatsReader statsReader, SSeasonKey<V> statsKey, int season) {
        SStatsKey<V> statKey = statsKey.season(season);
        String key = statKey.getKey();
        long value = statsReader.getStatKey(statKey).longValue();
        long before = stats.getStatsCollection().getCollection().countDocuments(new Document(key, new Document("$gt", value)));

        return before + 1;
    }

}
