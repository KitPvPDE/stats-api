package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableBooleanKey<K> extends IterableStatsKey<K, Boolean> {

    @Override
    Stream<? extends BooleanStatsKey<K>> stream();
}
