package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface LongStageKey<K> extends LongSeasonKey<K>, NumericStageKey<K, Long>,
        IterableLongKey<K> {

    @Override
    LongStatsKey<K> alltime();

    @Override
    LongStatsKey<K> season();

    @Override
    LongStatsKey<K> stage();

    @Override
    LongStatsKey<K> season(int season);

    @Override
    LongVoidStageKey bind(K k);

    @Override
    default Stream<? extends LongStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }
}
