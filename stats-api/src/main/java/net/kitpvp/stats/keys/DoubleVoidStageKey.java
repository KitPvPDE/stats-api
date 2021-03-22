package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface DoubleVoidStageKey extends NumericVoidStageKey<Double>, DoubleStageKey<Void>, VoidStageKey<Double>, DoubleVoidSeasonKey {

    @Override
    DoubleVoidStatsKey alltime();

    @Override
    DoubleVoidStatsKey season();

    @Override
    DoubleVoidStatsKey stage();

    @Override
    DoubleVoidStatsKey season(int season);

    @Override
    DoubleVoidStatsKey stage(int season, int stage);

    @Override
    default DoubleVoidStageKey bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends DoubleVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
