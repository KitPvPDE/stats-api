package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableIntVoidKey extends IterableIntKey<Void>, IterableNumericVoidKey<Integer> {

    @Override
    Stream<? extends IntVoidStatsKey> stream();
}
