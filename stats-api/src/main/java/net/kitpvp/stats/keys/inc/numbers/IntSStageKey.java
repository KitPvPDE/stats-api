package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.keys.inc.IncSSeasonKey;

public interface IntSStageKey extends IntStageKey<Void>, IncSSeasonKey<Integer> {

    @Override
    IntSStatsKey season(int season);

    @Override
    IntSStatsKey stage(int season, int stage);

    @Override
    default IntSStatsKey season() {
        return (IntSStatsKey) IntStageKey.super.season();
    }

    @Override
    default IntSStatsKey alltime() {
        return (IntSStatsKey) IntStageKey.super.alltime();
    }

    @Override
    default IntSStatsKey stage() {
        return (IntSStatsKey) IntStageKey.super.stage();
    }
}
