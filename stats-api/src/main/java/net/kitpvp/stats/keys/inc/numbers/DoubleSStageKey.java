package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.keys.inc.IncSSeasonKey;

public interface DoubleSStageKey extends DoubleStageKey<Void>, IncSSeasonKey<Double> {

    @Override
    DoubleSStatsKey season(int season);

    @Override
    DoubleSStatsKey stage(int season, int stage);

    @Override
    default DoubleSStatsKey season() {
        return (DoubleSStatsKey) DoubleStageKey.super.season();
    }

    @Override
    default DoubleSStatsKey alltime() {
        return (DoubleSStatsKey) DoubleStageKey.super.alltime();
    }

    default DoubleSStatsKey stage() {
        return (DoubleSStatsKey) DoubleStageKey.super.stage();
    }
}
