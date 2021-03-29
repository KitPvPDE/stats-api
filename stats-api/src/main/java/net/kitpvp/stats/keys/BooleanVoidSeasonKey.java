package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface BooleanVoidSeasonKey extends BooleanSeasonKey<Void>, VoidSeasonKey<Boolean>, IterableBooleanVoidKey {

    @Override
    BooleanVoidStatsKey alltime();

    @Override
    BooleanVoidStatsKey season();

    @Override
    BooleanVoidStatsKey season(int season);

    @Override
    default Stream<? extends BooleanVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season());
    }

    @Override
    default BooleanVoidSeasonKey bind(Void unused) {
        return this;
    }
}
