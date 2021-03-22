package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableStatsKey<K, V> extends IterableKey<K> {

    @Override
    Stream<? extends StatsKey<K, V>> stream();
}
