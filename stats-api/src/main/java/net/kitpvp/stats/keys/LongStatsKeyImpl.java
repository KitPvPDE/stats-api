package net.kitpvp.stats.keys;

import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

class LongStatsKeyImpl<K> extends NumericStatsKeyImpl<K, Long> implements LongStatsKey<K> {

    private final LongBinaryOperator addition;
    private final LongUnaryOperator reverse;
    private final long neutral, def, offset;

    LongStatsKeyImpl(KeyFunction<K> keyFunction, LongBinaryOperator addition, LongUnaryOperator inverse, long neutral, long def, long offset) {
        super(keyFunction, addition::applyAsLong, inverse::applyAsLong, neutral, def, offset);
        this.addition = addition;
        this.reverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public long applyLong(long i) {
        return this.addition.applyAsLong(i, this.offset);
    }

    @Override
    public long defLong() {
        return this.def;
    }

    @Override
    public long neutralLong() {
        return this.neutral;
    }

    @Override
    public long offsetLong() {
        return this.offset;
    }

    @Override
    public LongBinaryOperator additionLong() {
        return this.addition;
    }

    @Override
    public LongUnaryOperator inverseLong() {
        return this.reverse;
    }

    @Override
    public LongVoidStatsKey bind(K k) {
        return new LongVoidStatsKeyImpl(KeyFunctions.bind(keyFunction(), k),
                additionLong(), inverseLong(), neutralLong(),
                defLong(), offsetLong());
    }
}
