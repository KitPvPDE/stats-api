package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableVoidStatsKey<V> extends IterableVoidKey, IterableStatsKey<Void, V> {

    @Override
    Stream<? extends VoidStatsKey<V>> stream();
}
