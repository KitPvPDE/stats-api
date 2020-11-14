package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.NumericStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.LongStatsKey;

import java.util.function.Function;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

public class LongStatsKeyImpl<K> extends NumericStatsKeyImpl<K, Long> implements LongStatsKey<K> {

    private final LongBinaryOperator addition;
    private final LongUnaryOperator reverse;
    private final long neutral, def, offset;

    public LongStatsKeyImpl(Function<K, String> keyFunction, LongBinaryOperator addition, LongUnaryOperator inverse, long neutral, long def, long offset) {
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
    public LongBinaryOperator additionLong() {
        return this.addition;
    }

    @Override
    public LongUnaryOperator inverseLong() {
        return this.reverse;
    }
}
