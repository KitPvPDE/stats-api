package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncSSeasonKey;

public interface DoubleSSeasonKey extends DoubleSeasonKey<Void>, IncSSeasonKey<Double> {

    @Override
    DoubleSStatsKey season(int season);

    @Override
    default DoubleSStatsKey season() {
        return (DoubleSStatsKey) DoubleSeasonKey.super.season();
    }

    @Override
    default DoubleSStatsKey alltime() {
        return (DoubleSStatsKey) DoubleSeasonKey.super.alltime();
    }
}
