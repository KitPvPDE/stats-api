package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableLongKey<K> extends IterableNumericStatsKey<K, Long> {

    @Override
    Stream<? extends LongStatsKey<K>> stream();
}
