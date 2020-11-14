package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncStageKey;
import net.kitpvp.stats.season.Season;

public interface DoubleStageKey<K> extends DoubleSeasonKey<K>, IncStageKey<K, Double> {

    @Override
    DoubleStatsKey<K> season(int season);

    @Override
    default DoubleStatsKey<K> season() {
        return (DoubleStatsKey<K>) DoubleSeasonKey.super.season();
    }

    @Override
    default DoubleStatsKey<K> alltime() {
        return (DoubleStatsKey<K>) DoubleSeasonKey.super.alltime();
    }

    @Override
    DoubleStatsKey<K> stage(int season, int stage);

    @Override
    default DoubleStatsKey<K> stage() {
        return this.stage(Season.getSeason(), Season.getStage());
    }
}