package net.kitpvp.stats.keys.inc.number2;

import net.kitpvp.stats.keys.inc.IncSSeasonKey;
import net.kitpvp.stats.keys.inc.numbers.DoubleSStatsKey;

public interface DoubleSStageKey extends DoubleStageKey<Void>, IncSSeasonKey<Double> {

    @Override
    DoubleSStatsKey season(int season);

    @Override
    default DoubleSStatsKey season() {
        return (DoubleSStatsKey) DoubleStageKey.super.season();
    }

    @Override
    default DoubleSStatsKey alltime() {
        return (DoubleSStatsKey) DoubleStageKey.super.alltime();
    }
}
