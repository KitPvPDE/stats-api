package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IntVoidStageKey extends IntStageKey<Void>, NumericVoidStageKey<Integer>, IntVoidSeasonKey,
        IterableIntVoidKey {

    @Override
    IntVoidStatsKey alltime();

    @Override
    IntVoidStatsKey season();

    @Override
    IntVoidStatsKey stage();

    @Override
    IntVoidStatsKey season(int season);

    @Override
    default Stream<? extends IntVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }

    @Override
    default IntVoidStageKey bind(Void unused) {
        return this;
    }
}
