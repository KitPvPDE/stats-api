package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface LongSeasonKey<K> extends NumericSeasonKey<K, Long>, IterableLongKey<K> {

    @Override
    LongStatsKey<K> season();

    @Override
    LongStatsKey<K> alltime();

    @Override
    LongStatsKey<K> season(int season);

    LongVoidSeasonKey bind(K k);

    @Override
    default Stream<? extends LongStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
