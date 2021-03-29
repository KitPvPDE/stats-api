package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.*;
import net.kitpvp.stats.season.Season;

public interface DoubleReader extends Reader {

    default <K> double getDoubleKey(DoubleStatsKey<K> statsKey, K key) {
        return statsKey.applyDouble(statsKey.extractDouble(this, key));
    }

    default double getDoubleKey(DoubleVoidStatsKey statsKey) {
        return getDoubleKey(statsKey, null);
    }

    default <K> double getDoubleKey(DoubleSeasonKey<K> seasonKey, K key, int season) {
        return getDoubleKey(seasonKey.season(season), key);
    }

    default double getDoubleKey(DoubleVoidSeasonKey seasonKey, int season) {
        return getDoubleKey(seasonKey.season(season));
    }

    default <K> double getAlltimeDoubleKey(DoubleSeasonKey<K> seasonKey, K key) {
        return getDoubleKey(seasonKey, key, ALLTIME);
    }

    default double getAlltimeDoubleKey(DoubleVoidSeasonKey seasonKey) {
        return getDoubleKey(seasonKey, null, ALLTIME);
    }

    default <K> double getSeasonDoubleKey(DoubleSeasonKey<K> seasonKey, K key) {
        return getDoubleKey(seasonKey, key, Season.getSeason());
    }

    default double getSeasonDoubleKey(DoubleVoidSeasonKey seasonKey) {
        return getDoubleKey(seasonKey, null, Season.getSeason());
    }

    default <K> double getStageDoubleKey(DoubleStageKey<K> stageKey, K key) {
        return getDoubleKey(stageKey.stage(), key);
    }

    default double getStageDoubleKey(DoubleVoidStageKey stageKey) {
        return getDoubleKey(stageKey.stage());
    }
}
