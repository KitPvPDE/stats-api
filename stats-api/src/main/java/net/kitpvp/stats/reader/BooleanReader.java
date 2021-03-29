package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.*;
import net.kitpvp.stats.season.Season;

public interface BooleanReader extends Reader {

    default <K> boolean getBooleanKey(BooleanStatsKey<K> statsKey, K key) {
        return statsKey.applyBoolean(statsKey.extractBoolean(this, key));
    }

    default boolean getBooleanKey(BooleanVoidStatsKey statsKey) {
        return getBooleanKey(statsKey, null);
    }

    default <K> boolean getBooleanKey(BooleanSeasonKey<K> seasonKey, K key, int season) {
        return getBooleanKey(seasonKey.season(season), key);
    }

    default boolean getBooleanKey(BooleanVoidSeasonKey seasonKey, int season) {
        return getBooleanKey(seasonKey.season(season));
    }

    default <K> boolean getAlltimeBooleanKey(BooleanSeasonKey<K> seasonKey, K key) {
        return getBooleanKey(seasonKey, key, ALLTIME);
    }

    default boolean getAlltimeBooleanKey(BooleanVoidSeasonKey seasonKey) {
        return getBooleanKey(seasonKey, null, ALLTIME);
    }

    default <K> boolean getSeasonBooleanKey(BooleanSeasonKey<K> seasonKey, K key) {
        return getBooleanKey(seasonKey, key, Season.getSeason());
    }

    default boolean getSeasonBooleanKey(BooleanVoidSeasonKey seasonKey) {
        return getBooleanKey(seasonKey, null, Season.getSeason());
    }

    default <K> boolean getStageBooleanKey(BooleanStageKey<K> stageKey, K key) {
        return getBooleanKey(stageKey.stage(), key);
    }

    default boolean getStageBooleanKey(BooleanVoidStageKey stageKey) {
        return getBooleanKey(stageKey.stage());
    }
}
