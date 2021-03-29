package net.kitpvp.stats.keys;

import java.util.stream.Stream;

public interface IterableDoubleVoidKey extends IterableDoubleKey<Void>, IterableNumericVoidKey<Double> {

    @Override
    Stream<? extends DoubleVoidStatsKey> stream();
}
