package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableNumericStatsKey<K, V> extends IterableStatsKey<K, V> {

    @Override
    Stream<? extends NumericStatsKey<K, V>> stream();
}
