package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface LongVoidStageKey extends LongStageKey<Void>, NumericVoidStageKey<Long>, VoidStageKey<Long>,
        LongVoidSeasonKey, IterableLongVoidKey {

    @Override
    LongVoidStatsKey alltime();

    @Override
    LongVoidStatsKey season();

    @Override
    LongVoidStatsKey stage();

    @Override
    LongVoidStatsKey season(int season);

    default LongVoidStageKey bind(Void k) {
        return this;
    }

    @Override
    default Stream<? extends LongVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
