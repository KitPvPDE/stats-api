package net.kitpvp.stats.keys;

import java.util.Set;
import java.util.stream.Stream;

public interface IterableSetKey<K, X> extends IterableStatsKey<K, Set<X>> {

    @Override
    Stream<? extends SetStatsKey<K, X>> stream();
}
