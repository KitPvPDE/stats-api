package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableBooleanVoidKey extends IterableBooleanKey<Void>, IterableVoidStatsKey<Boolean> {

    @Override
    Stream<? extends BooleanVoidStatsKey> stream();
}
