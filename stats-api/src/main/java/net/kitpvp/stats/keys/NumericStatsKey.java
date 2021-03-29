package net.kitpvp.stats.keys;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public interface NumericStatsKey<K, V> extends StatsKey<K, V>, IterableNumericStatsKey<K, V> {

    static <K, V> NumericKeyBuilder<K, V> builder() {
        return new NumericKeyBuilder<>();
    }

    BinaryOperator<V> addition();

    UnaryOperator<V> inverse();

    V neutral();

    @Override
    V def();

    V offset();

    @Override
    String key(K k);

    @Override
    V apply(V v);

    @Override
    KeyFunction<K> keyFunction();

    @Override
    NumericVoidStatsKey<V> bind(K k);

    @Override
    default Stream<? extends NumericStatsKey<K, V>> stream() {
        return Stream.of(this);
    }
}
