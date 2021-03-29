package net.kitpvp.stats.keys;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;

class DoubleStageKeyImpl<K> extends StageKeyImpl<K, Double, DoubleStatsKey<K>> implements DoubleStageKey<K> {

    private final DoubleBinaryOperator sumFunction;
    private final DoubleUnaryOperator inverse;
    private final double neutral, def, offset;

    DoubleStageKeyImpl(KeyFunction<K> keyFunction, UnaryOperator<KeyFunction<K>> remapFunction, DoubleBinaryOperator sumFunction, DoubleUnaryOperator inverse, double neutral, double def, double offset) {
        super(keyFunction, remapFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.inverse = inverse;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public DoubleVoidStageKey bind(K k) {
        return new DoubleVoidStageKeyImpl(this.keyFunction.bind(k),
                function -> this.remapFunction.apply(this.keyFunction).bind(k),
                this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected DoubleStatsKey<K> createKey(KeyFunction<K> function) {
        return new DoubleStatsKeyImpl<>(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
