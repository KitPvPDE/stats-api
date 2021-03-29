package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IntSeasonKey<K> extends NumericSeasonKey<K, Integer>, IterableIntKey<K> {

    @Override
    IntStatsKey<K> season(int season);

    @Override
    IntStatsKey<K> season();

    @Override
    IntStatsKey<K> alltime();

    @Override
    Stream<? extends IntStatsKey<K>> stream();
}
