package net.kitpvp.stats.keys;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

class IntStageKeyImpl<K> extends StageKeyImpl<K, Integer, IntStatsKey<K>> implements IntStageKey<K> {

    private final IntBinaryOperator sumFunction;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    IntStageKeyImpl(KeyFunction<K> keyFunction, UnaryOperator<KeyFunction<K>> remapFunction, IntBinaryOperator sumFunction, IntUnaryOperator inverse, int neutral, int def, int offset) {
        super(keyFunction, remapFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public IntVoidStageKey bind(K k) {
        return new IntVoidStageKeyImpl(this.keyFunction.bind(k),
                function -> this.remapFunction.apply(this.keyFunction).bind(k),
                this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected IntStatsKey<K> createKey(KeyFunction<K> function) {
        return new IntStatsKeyImpl<>(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
