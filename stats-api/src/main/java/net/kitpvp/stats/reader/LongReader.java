package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.inc.numbers.*;
import net.kitpvp.stats.season.Season;

public interface LongReader extends Reader {

    default <K> long getLongKey(LongStatsKey<K> statsKey, K key) {
        return statsKey.applyLong(getStatKey(statsKey, key));
    }

    default long getLongKey(LongSStatsKey statsKey) {
        return statsKey.applyLong(getStatKey(statsKey, null));
    }

    default <K> long getLongKey(LongSeasonKey<K> seasonKey, K key, int season) {
        return getLongKey(seasonKey.season(season), key);
    }

    default long getLongKey(LongSSeasonKey seasonKey, int season) {
        return getLongKey(seasonKey.season(season));
    }

    default <K> long getLongKey(LongStageKey<K> stageKey, K key, int season, int stage) {
        return getLongKey(stageKey.stage(season, stage), key);
    }

    default long getLongKey(LongSStageKey stageKey, int season, int stage) {
        return getLongKey(stageKey.stage(season, stage));
    }

    default <K> long getAlltimeLongKey(LongSeasonKey<K> seasonKey, K key) {
        return getLongKey(seasonKey.alltime(), key);
    }

    default long getAlltimeLongKey(LongSSeasonKey seasonKey) {
        return getLongKey(seasonKey.alltime());
    }

    default <K> long getSeasonLongKey(LongSeasonKey<K> seasonKey, K key) {
        return getLongKey(seasonKey.season(), key);
    }

    default long getSeasonLongKey(LongSSeasonKey seasonKey) {
        return getLongKey(seasonKey.season());
    }

    default <K> long getStageLongKey(LongStageKey<K> stageKey, K key) {
        return getLongKey(stageKey.stage(), key);
    }

    default long getStageLongKey(LongSStageKey stageKey) {
        return getLongKey(stageKey.stage());
    }
}
