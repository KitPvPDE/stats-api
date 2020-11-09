package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.numeric.DoubleStatsKey;
import net.kitpvp.stats.keys.numeric.IntStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class DoubleStatsKeyImpl<K> implements DoubleStatsKey<K> {

    private final Function<K, String> keyFunction;
    private final DoubleBinaryOperator function;
    private final double neutral, def, offset;

    public DoubleStatsKeyImpl(Function<K, String> keyFunction, DoubleBinaryOperator function, double neutral, double def, double offset) {
        this.keyFunction = keyFunction;
        this.function = function;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public String key(K k) {
        return this.keyFunction.apply(k);
    }

    @Override
    public Double apply(Double integer) {
        return this.applyDouble(integer);
    }

    @Override
    public double applyDouble(double d) {
        return d + this.offset;
    }

    @Override
    public Double def() {
        return this.def;
    }

    @Override
    public double defDouble() {
        return this.def;
    }

    @Override
    public Double neutral() {
        return this.neutral;
    }

    @Override
    public double neutralDouble() {
        return this.neutral;
    }

    @Override
    public BinaryOperator<Double> function() {
        return this.function::applyAsDouble;
    }

    @Override
    public DoubleBinaryOperator doubleFunction() {
        return this.function;
    }
}
