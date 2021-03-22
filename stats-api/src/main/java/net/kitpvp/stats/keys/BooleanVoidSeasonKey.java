package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.stream.Stream;

public interface BooleanVoidSeasonKey extends BooleanSeasonKey<Void>, VoidSeasonKey<Boolean> {

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
