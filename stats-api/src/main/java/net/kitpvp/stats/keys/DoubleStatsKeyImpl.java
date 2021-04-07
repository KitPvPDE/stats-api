package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

class DoubleStatsKeyImpl<K> extends NumericStatsKeyImpl<K, Double> implements DoubleStatsKey<K> {

    static final DoubleStatsKey<String> IDENTITY = DoubleStatsKey.<String>builder()
            .keyBuilder(builder -> builder.key(Key.identity()))
            .build();

    private final KeyFunction<K> keyFunction;
    private final DoubleBinaryOperator addition;
    private final DoubleUnaryOperator inverse;
    private final double neutral, def, offset;

    DoubleStatsKeyImpl(KeyFunction<K> keyFunction, DoubleBinaryOperator addition, DoubleUnaryOperator inverse, double neutral, double def, double offset) {
        super(keyFunction, addition::applyAsDouble, inverse::applyAsDouble, neutral, def, offset);
        this.keyFunction = keyFunction;
        this.addition = addition;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public DoubleVoidStatsKey bind(K k) {
        return new DoubleVoidStatsKeyImpl(this.keyFunction.bind(k), this.addition, this.inverse, this.neutral, this.def, this.offset);
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
