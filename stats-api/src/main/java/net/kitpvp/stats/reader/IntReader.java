package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.numeric.*;
import net.kitpvp.stats.season.Season;

public interface IntReader extends Reader {

    default <K> int getIntKey(IntStatsKey<K> statsKey, K key) {
        return statsKey.applyInt(getStatKey(statsKey, key));
    }

    default int getIntKey(IntSStatsKey statsKey) {
        return statsKey.applyInt(getStatKey(statsKey, null));
    }

    default <K> int getIntKey(IntSeasonKey<K> seasonKey, K key, int season) {
        return getIntKey(seasonKey.season(season), key);
    }

    default int getIntKey(IntSSeasonKey seasonKey, int season) {
        return getIntKey(seasonKey.season(season));
    }

    default <K> int getIntKey(IntStageKey<K> stageKey, K key, int season, int stage) {
        return getIntKey(stageKey.stage(season, stage), key);
    }

    default int getIntKey(IntSStageKey stageKey, int season, int stage) {
        return getIntKey(stageKey.stage(season, stage));
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

    default <K> int getStageIntKey(IntStageKey<K> stageKey, K key) {
        return getIntKey(stageKey.stage(), key);
    }

    default int getStageIntKey(IntSStageKey stageKey) {
        return getIntKey(stageKey.stage());
    }
}
