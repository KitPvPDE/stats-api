package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.builder.numeric.DoubleKeyBuilder;
import net.kitpvp.stats.keys.IncStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

public interface DoubleStatsKey<K> extends IncStatsKey<K, Double> {

    static <K> DoubleKeyBuilder<K> builder() {
        return new DoubleKeyBuilder<>();
    }

    BinaryOperator<Double> addition();

    DoubleBinaryOperator additionDouble();

    UnaryOperator<Double> inverse();

    DoubleUnaryOperator inverseDouble();

    @Override
    @Deprecated
    Double neutral();

    double neutralDouble();

    @Override
    @Deprecated
    Double def();

    double defDouble();

    @Override
    Double apply(Double integer);

    double applyDouble(double d);

    @Override
    String key(K k);
}
