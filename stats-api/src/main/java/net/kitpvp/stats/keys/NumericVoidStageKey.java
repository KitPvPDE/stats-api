package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface NumericVoidStageKey<V> extends NumericVoidSeasonKey<V>, NumericStageKey<Void, V>, VoidStageKey<V>,
        IterableNumericVoidKey<V> {

    @Override
    NumericVoidStatsKey<V> stage();

    @Override
    NumericVoidStatsKey<V> season();

    @Override
    NumericVoidStatsKey<V> alltime();

    @Override
    NumericVoidStatsKey<V> season(int season);

    @Override
    default Stream<? extends NumericVoidStatsKey<V>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }

    @Override
    default NumericVoidStageKey<V> bind(Void unused) {
        return this;
    }
}
