package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface DoubleVoidSeasonKey extends DoubleSeasonKey<Void>, NumericVoidSeasonKey<Double>,
        IterableDoubleVoidKey {

    @Override
    DoubleVoidStatsKey alltime();

    @Override
    DoubleVoidStatsKey season();

    @Override
    DoubleVoidStatsKey season(int season);

    @Override
    default DoubleVoidSeasonKey bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends DoubleVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
