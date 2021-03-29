package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableNumericVoidKey<V> extends IterableNumericStatsKey<Void, V> {

    @Override
    Stream<? extends NumericVoidStatsKey<V>> stream();
}
