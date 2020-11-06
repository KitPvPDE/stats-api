package net.kitpvp.stats.reader;

import net.kitpvp.stats.season.Season;
import net.kitpvp.stats.keys.inc.numbers.IntSSeasonKey;
import net.kitpvp.stats.keys.inc.numbers.IntSStatsKey;
import net.kitpvp.stats.keys.inc.numbers.IntSeasonKey;
import net.kitpvp.stats.keys.inc.numbers.IntStatsKey;

public interface IntReader extends Reader {

    default <K> int getIntKey(IntStatsKey<K> statsKey, K key) {
        return statsKey.applyInt(getStatKey(statsKey, key));
    }

    default int getIntKey(IntSStatsKey statsKey) {
        return getIntKey(statsKey, null);
    }

    default <K> int getIntKey(IntSeasonKey<K> seasonKey, K key, int season) {
        return getIntKey(seasonKey.season(season), key);
    }

    default int getIntKey(IntSSeasonKey seasonKey, int season) {
        return getIntKey(seasonKey.season(season));
    }

    default <K> int getAlltimeIntKey(IntSeasonKey<K> seasonKey, K key) {
        return getIntKey(seasonKey, key, ALLTIME);
    }

    default int getAlltimeIntKey(IntSSeasonKey seasonKey) {
        return getIntKey(seasonKey, null, ALLTIME);
    }

    default <K> int getSeasonIntKey(IntSeasonKey<K> seasonKey, K key) {
        return getIntKey(seasonKey, key, Season.getSeason());
    }

    default int getSeasonIntKey(IntSSeasonKey seasonKey) {
        return getIntKey(seasonKey, null, Season.getSeason());
    }
}
