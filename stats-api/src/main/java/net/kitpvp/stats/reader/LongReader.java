package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.*;

public interface LongReader extends Reader {

    default <K> long getLongKey(LongStatsKey<K> statsKey, K key) {
        return statsKey.applyLong(statsKey.extractLong(this, key));
    }

    default long getLongKey(LongVoidStatsKey statsKey) {
        return this.getLongKey(statsKey, null);
    }

    default <K> long getLongKey(LongSeasonKey<K> seasonKey, K key, int season) {
        return getLongKey(seasonKey.season(season), key);
    }

    default long getLongKey(LongVoidSeasonKey seasonKey, int season) {
        return getLongKey(seasonKey.season(season));
    }

    default <K> long getAlltimeLongKey(LongSeasonKey<K> seasonKey, K key) {
        return getLongKey(seasonKey.alltime(), key);
    }

    default long getAlltimeLongKey(LongVoidSeasonKey seasonKey) {
        return getLongKey(seasonKey.alltime());
    }

    default <K> long getSeasonLongKey(LongSeasonKey<K> seasonKey, K key) {
        return getLongKey(seasonKey.season(), key);
    }

    default long getSeasonLongKey(LongVoidSeasonKey seasonKey) {
        return getLongKey(seasonKey.season());
    }

    default <K> long getStageLongKey(LongStageKey<K> stageKey, K key) {
        return getLongKey(stageKey.stage(), key);
    }

    default long getStageLongKey(LongVoidStageKey stageKey) {
        return getLongKey(stageKey.stage());
    }
}
