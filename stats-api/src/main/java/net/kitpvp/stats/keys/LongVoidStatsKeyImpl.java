package net.kitpvp.stats.keys;

import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

class LongVoidStatsKeyImpl extends NumericVoidStatsKeyImpl<Long> implements LongVoidStatsKey {

    private final LongBinaryOperator function;
    private final LongUnaryOperator inverse;
    private final long neutral, def, offset;

    LongVoidStatsKeyImpl(VoidKeyFunction keyFunction, LongBinaryOperator function, LongUnaryOperator inverse, long neutral, long def, long offset) {
        super(keyFunction, function::applyAsLong, inverse::applyAsLong, neutral, def, offset);
        this.function = function;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public long applyLong(long i) {
        return i + this.offset;
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
        return this.function;
    }

    @Override
    public LongUnaryOperator inverseLong() {
        return this.inverse;
    }

    @Override
    public LongVoidStatsKey bind(Void unused) {
        return this;
    }
}
