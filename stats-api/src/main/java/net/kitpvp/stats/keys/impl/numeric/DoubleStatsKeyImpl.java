package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.impl.NumericStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.DoubleStatsKey;

import java.util.function.*;

public class DoubleStatsKeyImpl<K> extends NumericStatsKeyImpl<K, Double> implements DoubleStatsKey<K> {

    private final KeyFunction<K> keyFunction;
    private final DoubleBinaryOperator addition;
    private final DoubleUnaryOperator inverse;
    private final double neutral, def, offset;

    public DoubleStatsKeyImpl(KeyFunction<K> keyFunction, DoubleBinaryOperator addition, DoubleUnaryOperator inverse, double neutral, double def, double offset) {
        super(keyFunction, addition::applyAsDouble, inverse::applyAsDouble, neutral, def, offset);
        this.keyFunction = keyFunction;
        this.addition = addition;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public double applyDouble(double d) {
        return this.addition.applyAsDouble(d, this.offset);
    }

    @Override
    public double defDouble() {
        return this.def;
    }

    @Override
    public double neutralDouble() {
        return this.neutral;
    }

    @Override
    public double offsetDouble() {
        return this.offset;
    }

    @Override
    public DoubleBinaryOperator additionDouble() {
        return this.addition;
    }

    @Override
    public DoubleUnaryOperator inverseDouble() {
        return this.inverse;
    }
}
