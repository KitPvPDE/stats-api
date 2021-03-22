package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.stream.Stream;

public interface NumericVoidStageKey<V> extends NumericVoidSeasonKey<V>, NumericStageKey<Void, V>, VoidStageKey<V> {

    @Override
    NumericVoidStatsKey<V> stage();

    @Override
    NumericVoidStatsKey<V> season();

    @Override
    NumericVoidStatsKey<V> alltime();

    @Override
    NumericVoidStatsKey<V> season(int season);

    @Override
    NumericVoidStatsKey<V> stage(int season, int stage);

    @Override
    default Stream<? extends NumericVoidStatsKey<V>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }

    @Override
    default NumericVoidStageKey<V> bind(Void unused) {
        return this;
    }
}
