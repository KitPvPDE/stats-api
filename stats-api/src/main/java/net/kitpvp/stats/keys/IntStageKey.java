package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IntStageKey<K> extends IntSeasonKey<K>, NumericStageKey<K, Integer>,
        IterableIntKey<K> {

    @Override
    IntStatsKey<K> alltime();

    @Override
    IntStatsKey<K> season();

    @Override
    IntStatsKey<K> stage();

    @Override
    IntStatsKey<K> season(int season);

    @Override
    IntVoidStageKey bind(K k);

    @Override
    default Stream<? extends IntStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
