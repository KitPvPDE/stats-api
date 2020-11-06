package net.kitpvp.stats.reader;

import net.kitpvp.stats.season.Season;
import net.kitpvp.stats.keys.inc.numbers.LongSSeasonKey;
import net.kitpvp.stats.keys.inc.numbers.LongSStatsKey;
import net.kitpvp.stats.keys.inc.numbers.LongSeasonKey;
import net.kitpvp.stats.keys.inc.numbers.LongStatsKey;

public interface LongReader extends Reader {

    default <K> long getLongKey(LongStatsKey<K> statsKey, K key) {
        return statsKey.applyLong(getStatKey(statsKey, key));
    }

    default long getLongKey(LongSStatsKey statsKey) {
        return getLongKey(statsKey, null);
    }

    default <K> long getLongKey(LongSeasonKey<K> seasonKey, K key, int season) {
        return getLongKey(seasonKey.season(season), key);
    }

    default long getLongKey(LongSSeasonKey seasonKey, int season) {
        return getLongKey(seasonKey.season(season));
    }

    default <K> long getAlltimeLongKey(LongSeasonKey<K> seasonKey, K key) {
        return getLongKey(seasonKey, key, ALLTIME);
    }

    default long getAlltimeLongKey(LongSSeasonKey seasonKey) {
        return getLongKey(seasonKey, null, ALLTIME);
    }

    default <K> long getSeasonLongKey(LongSeasonKey<K> seasonKey, K key) {
        return getLongKey(seasonKey, key, Season.getSeason());
    }

    default long getSeasonLongKey(LongSSeasonKey seasonKey) {
        return getLongKey(seasonKey, null, Season.getSeason());
    }
}
