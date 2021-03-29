package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface NumericSeasonKey<K, V> extends SeasonKey<K, V>, IterableNumericStatsKey<K, V> {

    @Override
    NumericStatsKey<K, V> season();

    @Override
    NumericStatsKey<K, V> alltime();

    @Override
    NumericStatsKey<K, V> season(int season);

    @Override
    NumericVoidSeasonKey<V> bind(K k);

    @Override
    default Stream<? extends NumericStatsKey<K, V>> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
