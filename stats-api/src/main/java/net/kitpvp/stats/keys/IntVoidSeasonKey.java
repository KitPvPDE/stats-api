package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IntVoidSeasonKey extends IntSeasonKey<Void>, NumericVoidSeasonKey<Integer>,
        IterableIntVoidKey {

    @Override
    IntVoidStatsKey alltime();

    @Override
    IntVoidStatsKey season();

    @Override
    IntVoidStatsKey season(int season);

    @Override
    default IntVoidSeasonKey bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends IntVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
