package net.kitpvp.stats.keys;

import java.util.function.BiFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.UnaryOperator;

class LongStageKeyImpl<K> extends StageKeyImpl<K, Long, LongStatsKey<K>> implements LongStageKey<K> {

    private final LongBinaryOperator addition;
    private final LongUnaryOperator inverse;
    private final long neutral, def, offset;

    LongStageKeyImpl(KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, UnaryOperator<KeyFunction<K>> remapFunction, LongBinaryOperator addition, LongUnaryOperator inverse, long neutral, long def, long offset) {
        super(keyFunction, seasonKeyMapping, remapFunction);
        this.addition = addition;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public LongVoidStageKey bind(K k) {
        return new LongVoidStageKeyImpl(this.keyFunction.bind(k),
                function -> this.remapFunction.apply(this.keyFunction).bind(k),
                this.addition, this.inverse, this.neutral, this.def, this.offset);
    }

    public long defLong() {
        return this.def;
    }

    public long neutralLong() {
        return this.neutral;
    }

    public long offsetLong() {
        return this.offset;
    }

    public LongBinaryOperator additionLong() {
        return this.addition;
    }

    public LongUnaryOperator inverseLong() {
        return this.inverse;
    }

    @Override
    protected LongStatsKey<K> createKey(KeyFunction<K> function) {
        return new LongStatsKeyImpl<>(function, this.addition, this.inverse, this.neutral, this.def, this.offset);
    }
}
