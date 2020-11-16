package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.NumericVoidStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.DoubleSStatsKey;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class DoubleVoidStatsKeyImpl extends NumericVoidStatsKeyImpl<Double> implements DoubleSStatsKey {

    private final DoubleBinaryOperator function;
    private final DoubleUnaryOperator inverse;
    private final double neutral, def, offset;

    public DoubleVoidStatsKeyImpl(Function<Void, String> keyFunction, DoubleBinaryOperator function, DoubleUnaryOperator inverse, double neutral, double def, double offset) {
        super(keyFunction, function::applyAsDouble, inverse::applyAsDouble, neutral, def, offset);
        this.function = function;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public double applyDouble(double d) {
        return d + this.offset;
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
        return this.function;
    }

    @Override
    public DoubleUnaryOperator inverseDouble() {
        return this.inverse;
    }
}
