package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface NumericVoidSeasonKey<V> extends NumericSeasonKey<Void, V>, VoidSeasonKey<V>,
        IterableNumericVoidKey<V> {

    @Override
    NumericVoidStatsKey<V> alltime();

    @Override
    NumericVoidStatsKey<V> season();

    @Override
    NumericVoidStatsKey<V> season(int season);

    @Override
    default Stream<? extends NumericVoidStatsKey<V>> stream() {
        return Stream.of(this.alltime(), this.season());
    }

    @Override
    default NumericVoidSeasonKey<V> bind(Void unused) {
        return this;
    }
}
