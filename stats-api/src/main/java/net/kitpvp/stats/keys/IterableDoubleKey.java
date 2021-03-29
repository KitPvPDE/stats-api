package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableDoubleKey<K> extends IterableNumericStatsKey<K, Double> {

    @Override
    Stream<? extends DoubleStatsKey<K>> stream();
}
