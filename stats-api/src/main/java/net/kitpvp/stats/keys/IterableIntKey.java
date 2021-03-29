package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableIntKey<K> extends IterableNumericStatsKey<K, Integer> {

    @Override
    Stream<? extends IntStatsKey<K>> stream();
}
