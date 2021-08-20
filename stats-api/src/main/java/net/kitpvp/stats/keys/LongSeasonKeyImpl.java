package net.kitpvp.stats.keys;

import java.util.function.BiFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

class LongSeasonKeyImpl<K> extends SeasonKeyImpl<K, Long, LongStatsKey<K>> implements LongSeasonKey<K> {

    private final LongBinaryOperator addition;
    private final LongUnaryOperator inverse;
    private final long neutral, def, offset;

    LongSeasonKeyImpl(KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, LongBinaryOperator addition, LongUnaryOperator inverse, long neutral, long def, long offset) {
        super(keyFunction, seasonKeyMapping);
        this.addition = addition;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
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
    public LongVoidSeasonKey bind(K k) {
        return new LongVoidSeasonKeyImpl(this.keyFunction.bind(k), this.addition, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected LongStatsKey<K> createKey(KeyFunction<K> function) {
        return new LongStatsKeyImpl<>(function, this.addition, this.inverse, this.neutral, this.def, this.offset);
    }
}
