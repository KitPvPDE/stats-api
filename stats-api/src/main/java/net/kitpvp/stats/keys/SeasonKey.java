package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface SeasonKey<K, V> extends IterableStatsKey<K, V> {

    StatsKey<K, V> alltime();

    StatsKey<K, V> season();

    StatsKey<K, V> season(int season);

    VoidSeasonKey<V> bind(K k);

    @Override
    Stream<? extends StatsKey<K, V>> stream();
}
