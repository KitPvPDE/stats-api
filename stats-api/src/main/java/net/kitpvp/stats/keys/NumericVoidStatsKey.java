package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public interface NumericVoidStatsKey<V> extends NumericStatsKey<Void, V>, VoidStatsKey<V> {

    static <V> NumericVoidKeyBuilder<V> builder() {
        return new NumericVoidKeyBuilder<>();
    }

    @Override
    BinaryOperator<V> addition();

    @Override
    UnaryOperator<V> inverse();

    @Override
    V neutral();

    @Override
    V def();

    @Override
    String key(Void unused);

    @Override
    V apply(V v);

    @Override
    default NumericVoidStatsKey<V> bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends NumericVoidStatsKey<V>> stream() {
        return Stream.of(this);
    }
}
