package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.bool.*;
import net.kitpvp.stats.season.Season;

public interface BooleanReader extends Reader {

    default <K> boolean getBooleanKey(BooleanStatsKey<K> statsKey, K key) {
        return statsKey.applyBoolean(readStatKey(statsKey, key));
    }

    default boolean getBooleanKey(BooleanSStatsKey statsKey) {
        return getBooleanKey(statsKey, null);
    }

    default <K> boolean getBooleanKey(BooleanSeasonKey<K> seasonKey, K key, int season) {
        return getBooleanKey(seasonKey.season(season), key);
    }

    default boolean getBooleanKey(BooleanSSeasonKey seasonKey, int season) {
        return getBooleanKey(seasonKey.season(season));
    }

    default <K> boolean getBooleanKey(BooleanStageKey<K> seasonKey, K key, int season, int stage) {
        return getBooleanKey(seasonKey.stage(season, stage), key);
    }

    default <K> boolean getAlltimeBooleanKey(BooleanSeasonKey<K> seasonKey, K key) {
        return getBooleanKey(seasonKey, key, ALLTIME);
    }

    default boolean getAlltimeBooleanKey(BooleanSSeasonKey seasonKey) {
        return getBooleanKey(seasonKey, null, ALLTIME);
    }

    default <K> boolean getSeasonBooleanKey(BooleanSeasonKey<K> seasonKey, K key) {
        return getBooleanKey(seasonKey, key, Season.getSeason());
    }

    default boolean getSeasonBooleanKey(BooleanSSeasonKey seasonKey) {
        return getBooleanKey(seasonKey, null, Season.getSeason());
    }

    default <K> boolean getStageBooleanKey(BooleanStageKey<K> stageKey, K key) {
        return getBooleanKey(stageKey, key, Season.getSeason(), Season.getStage());
    }

    default boolean getStageBooleanKey(BooleanSStageKey stageKey) {
        return getBooleanKey(stageKey, null, Season.getSeason(), Season.getStage());
    }
}
