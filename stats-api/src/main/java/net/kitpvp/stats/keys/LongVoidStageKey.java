package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.stream.Stream;

public interface LongVoidStageKey extends LongStageKey<Void>, NumericVoidStageKey<Long>, VoidStageKey<Long>, LongVoidSeasonKey {

    @Override
    LongVoidStatsKey alltime();

    @Override
    LongVoidStatsKey season();

    @Override
    LongVoidStatsKey stage();

    @Override
    LongVoidStatsKey season(int season);

    @Override
    LongVoidStatsKey stage(int season, int stage);

    default LongVoidStageKey bind(Void k) {
        return this;
    }

    @Override
    default Stream<? extends LongVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
