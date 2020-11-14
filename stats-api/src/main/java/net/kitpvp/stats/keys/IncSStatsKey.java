package net.kitpvp.stats.keys;

import net.kitpvp.stats.builder.numeric.NumericVoidKeyBuilder;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public interface IncSStatsKey<V> extends IncStatsKey<Void, V>, SStatsKey<V> {

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
}
