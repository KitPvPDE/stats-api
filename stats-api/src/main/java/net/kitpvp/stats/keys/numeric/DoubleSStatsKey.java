package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.builder.numeric.DoubleVoidKeyBuilder;
import net.kitpvp.stats.keys.IncSStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;

public interface DoubleSStatsKey extends DoubleStatsKey<Void>, IncSStatsKey<Double> {

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
}
