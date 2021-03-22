package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;

import java.util.stream.Stream;

public interface IntStageKey<K> extends IntSeasonKey<K>, NumericStageKey<K, Integer> {

    @Override
    IntStatsKey<K> alltime();

    @Override
    IntStatsKey<K> season();

    @Override
    IntStatsKey<K> stage();

    @Override
    IntStatsKey<K> season(int season);

    @Override
    IntStatsKey<K> stage(int season, int stage);

    @Override
    IntVoidStageKey bind(K k);

    @Override
    default Stream<? extends IntStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
