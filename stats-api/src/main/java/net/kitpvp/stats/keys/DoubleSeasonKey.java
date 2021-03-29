package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface DoubleSeasonKey<K> extends NumericSeasonKey<K, Double>, IterableDoubleKey<K> {

    @Override
    DoubleStatsKey<K> alltime();

    @Override
    DoubleStatsKey<K> season();

    @Override
    DoubleStatsKey<K> season(int season);

    DoubleVoidSeasonKey bind(K k);

    @Override
    default Stream<? extends DoubleStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
