package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.*;
import net.kitpvp.stats.season.Season;

public interface IntReader extends Reader {

    default <K> int getIntKey(IntStatsKey<K> statsKey, K key) {
        return statsKey.applyInt(statsKey.extractInt(this, key));
    }

    default int getIntKey(IntVoidStatsKey statsKey) {
        return statsKey.applyInt(statsKey.extractInt(this, null));
    }

    default <K> int getIntKey(IntSeasonKey<K> seasonKey, K key, int season) {
        return getIntKey(seasonKey.season(season), key);
    }

    default int getIntKey(IntVoidSeasonKey seasonKey, int season) {
        return getIntKey(seasonKey.season(season));
    }

    default <K> int getAlltimeIntKey(IntSeasonKey<K> seasonKey, K key) {
        return getIntKey(seasonKey, key, ALLTIME);
    }

    default int getAlltimeIntKey(IntVoidSeasonKey seasonKey) {
        return getIntKey(seasonKey, null, ALLTIME);
    }

    default <K> int getSeasonIntKey(IntSeasonKey<K> seasonKey, K key) {
        return getIntKey(seasonKey, key, Season.getSeason());
    }

    default int getSeasonIntKey(IntVoidSeasonKey seasonKey) {
        return getIntKey(seasonKey, null, Season.getSeason());
    }

    default <K> int getStageIntKey(IntStageKey<K> stageKey, K key) {
        return getIntKey(stageKey.stage(), key);
    }

    default int getStageIntKey(IntVoidStageKey stageKey) {
        return getIntKey(stageKey.stage());
    }
}
