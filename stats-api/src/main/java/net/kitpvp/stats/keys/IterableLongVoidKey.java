package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableLongVoidKey extends IterableLongKey<Void>, IterableNumericVoidKey<Long> {

    @Override
    Stream<? extends LongVoidStatsKey> stream();
}
