package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.keys.inc.IncStageKey;
import net.kitpvp.stats.keys.inc.numbers.DoubleSeasonKey;
import net.kitpvp.stats.keys.inc.numbers.DoubleStatsKey;
import net.kitpvp.stats.keys.inc.numbers.IntSeasonKey;
import net.kitpvp.stats.keys.inc.numbers.IntStatsKey;
import net.kitpvp.stats.season.Season;

public interface IntStageKey<K> extends IntSeasonKey<K>, IncStageKey<K, Integer> {

    @Override
    IntStatsKey<K> season(int season);

    @Override
    default IntStatsKey<K> season() {
        return (IntStatsKey<K>) IntSeasonKey.super.season();
    }

    @Override
    default IntStatsKey<K> alltime() {
        return (IntStatsKey<K>) IntSeasonKey.super.alltime();
    }

    @Override
    IntStatsKey<K> stage(int season, int stage);

    @Override
    default IntStatsKey<K> stage() {
        return this.stage(Season.getSeason(), Season.getStage());
    }
}
