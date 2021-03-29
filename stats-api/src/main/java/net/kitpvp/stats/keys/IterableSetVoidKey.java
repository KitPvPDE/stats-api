package net.kitpvp.stats.keys;

import java.util.Set;
import java.util.stream.Stream;

public interface IterableSetVoidKey<X> extends IterableSetKey<Void, X>, IterableVoidStatsKey<Set<X>> {

    @Override
    Stream<? extends SetVoidStatsKey<X>> stream();
}
