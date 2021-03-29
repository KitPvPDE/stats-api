package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface BooleanVoidStageKey extends BooleanStageKey<Void>, VoidStageKey<Boolean>,
        BooleanVoidSeasonKey, IterableBooleanVoidKey {

    @Override
    BooleanVoidStatsKey alltime();

    @Override
    BooleanVoidStatsKey season();

    @Override
    BooleanVoidStatsKey stage();

    @Override
    BooleanVoidStatsKey season(int season);

    @Override
    default BooleanVoidStageKey bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends BooleanVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
