package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.stream.Stream;

public interface IntVoidStageKey extends IntStageKey<Void>, NumericVoidStageKey<Integer>, IntVoidSeasonKey {

    @Override
    IntVoidStatsKey alltime();

    @Override
    IntVoidStatsKey season();

    @Override
    IntVoidStatsKey stage();

    @Override
    IntVoidStatsKey season(int season);

    @Override
    IntVoidStatsKey stage(int season, int stage);

    @Override
    default Stream<? extends IntVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }

    @Override
    default IntVoidStageKey bind(Void unused) {
        return this;
    }
}
