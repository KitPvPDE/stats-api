package net.kitpvp.stats.keys;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

class DoubleVoidStageKeyImpl extends VoidStageKeyImpl<Double, DoubleVoidStatsKey> implements DoubleVoidStageKey {

    private final DoubleBinaryOperator sumFunction;
    private final DoubleUnaryOperator inverse;
    private final double neutral, def, offset;

    DoubleVoidStageKeyImpl(VoidKeyFunction keyFunction, UnaryOperator<VoidKeyFunction> remapFunction, DoubleBinaryOperator sumFunction, DoubleUnaryOperator inverse, double neutral, double def, double offset) {
        super(keyFunction, remapFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected DoubleVoidStatsKey createKey(VoidKeyFunction function) {
        return new DoubleVoidStatsKeyImpl(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
