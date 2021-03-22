package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;

import java.util.stream.Stream;

public interface LongStageKey<K> extends LongSeasonKey<K>, NumericStageKey<K, Long> {

    @Override
    LongStatsKey<K> alltime();

    @Override
    LongStatsKey<K> season();

    @Override
    LongStatsKey<K> stage();

    @Override
    LongStatsKey<K> season(int season);

    @Override
    LongStatsKey<K> stage(int season, int stage);

    @Override
    LongVoidStageKey bind(K k);

    @Override
    default Stream<? extends LongStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
