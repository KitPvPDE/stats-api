package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.stream.Stream;

public interface LongVoidSeasonKey extends LongSeasonKey<Void>, NumericVoidSeasonKey<Long> {

    @Override
    LongVoidStatsKey season();

    @Override
    LongVoidStatsKey alltime();

    @Override
    LongVoidStatsKey season(int season);

    @Override
    default LongVoidSeasonKey bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends LongVoidStatsKey> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
