package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface DoubleStageKey<K> extends DoubleSeasonKey<K>, NumericStageKey<K, Double> {

    @Override
    DoubleStatsKey<K> season();

    @Override
    DoubleStatsKey<K> alltime();

    @Override
    DoubleStatsKey<K> stage();

    @Override
    DoubleVoidStageKey bind(K k);

    @Override
    DoubleStatsKey<K> stage(int season, int stage);

    @Override
    DoubleStatsKey<K> season(int season);

    @Override
    default Stream<? extends DoubleStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
