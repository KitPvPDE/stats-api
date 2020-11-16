package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.keys.AppendableIncKey;
import net.kitpvp.stats.builder.numeric.NumericKeyBuilder;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public interface IncStatsKey<K, V> extends StatsKey<K, V>, AppendableIncKey<K, V> {

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
}
