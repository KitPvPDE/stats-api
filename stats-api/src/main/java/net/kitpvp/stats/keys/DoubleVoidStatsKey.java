package net.kitpvp.stats.keys;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Stream;

public interface DoubleVoidStatsKey extends DoubleStatsKey<Void>, NumericVoidStatsKey<Double>,
        IterableDoubleVoidKey {

    static DoubleVoidKeyBuilder builder() {
        return new DoubleVoidKeyBuilder();
    }

    @Override
    BinaryOperator<Double> addition();

    @Override
    DoubleBinaryOperator additionDouble();

    @Override
    Double neutral();

    @Override
    double neutralDouble();

    @Override
    Double def();

    @Override
    double defDouble();

    @Override
    Double apply(Double integer);

    @Override
    double applyDouble(double d);

    @Override
    String key(Void unused);

    @Override
    default DoubleVoidStatsKey bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends DoubleVoidStatsKey> stream() {
        return Stream.of(this);
    }
}
