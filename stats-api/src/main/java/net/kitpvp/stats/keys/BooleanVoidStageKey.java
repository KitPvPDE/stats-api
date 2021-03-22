package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.stream.Stream;

public interface BooleanVoidStageKey extends BooleanStageKey<Void>, VoidSeasonKey<Boolean>, VoidStageKey<Boolean>, BooleanVoidSeasonKey {

    @Override
    BooleanVoidStatsKey alltime();

    @Override
    BooleanVoidStatsKey season();

    @Override
    BooleanVoidStatsKey stage();

    @Override
    BooleanVoidStatsKey season(int season);

    @Override
    BooleanVoidStatsKey stage(int season, int stage);

    @Override
    default BooleanVoidStageKey bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends BooleanVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
