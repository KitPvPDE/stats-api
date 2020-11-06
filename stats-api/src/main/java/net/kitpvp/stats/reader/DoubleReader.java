package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.inc.numbers.*;
import net.kitpvp.stats.season.Season;

public interface DoubleReader extends Reader {

    default <K> double getDoubleKey(DoubleStatsKey<K> statsKey, K key) {
        return statsKey.applyDouble(getStatKey(statsKey, key));
    }

    default double getDoubleKey(DoubleSStatsKey statsKey) {
        return getDoubleKey(statsKey, null);
    }

    default <K> double getDoubleKey(DoubleSeasonKey<K> seasonKey, K key, int season) {
        return getDoubleKey(seasonKey.season(season), key);
    }

    default double getDoubleKey(DoubleSSeasonKey seasonKey, int season) {
        return getDoubleKey(seasonKey.season(season));
    }

    default <K> double getDoubleKey(DoubleStageKey<K> seasonKey, K key, int season, int stage) {
        return getDoubleKey(seasonKey.stage(season, stage), key);
    }

    default <K> double getAlltimeDoubleKey(DoubleSeasonKey<K> seasonKey, K key) {
        return getDoubleKey(seasonKey, key, ALLTIME);
    }

    default double getAlltimeDoubleKey(DoubleSSeasonKey seasonKey) {
        return getDoubleKey(seasonKey, null, ALLTIME);
    }

    default <K> double getSeasonDoubleKey(DoubleSeasonKey<K> seasonKey, K key) {
        return getDoubleKey(seasonKey, key, Season.getSeason());
    }

    default double getSeasonDoubleKey(DoubleSSeasonKey seasonKey) {
        return getDoubleKey(seasonKey, null, Season.getSeason());
    }

    default <K> double getStageDoubleKey(DoubleStageKey<K> stageKey, K key) {
        return getDoubleKey(stageKey, key, Season.getSeason(), Season.getStage());
    }

    default double getStageDoubleKey(DoubleSStageKey stageKey) {
        return getDoubleKey(stageKey, null, Season.getSeason(), Season.getStage());
    }
}
