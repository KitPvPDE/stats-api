package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.impl.StageKeyImpl;
import net.kitpvp.stats.keys.numeric.LongStageKey;
import net.kitpvp.stats.keys.numeric.LongStatsKey;

import java.util.function.Function;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

public class LongStageKeyImpl<K> extends StageKeyImpl<K, Long, LongStatsKey<K>> implements LongStageKey<K> {

    private final LongBinaryOperator addition;
    private final LongUnaryOperator inverse;
    private final long neutral, def, offset;

    public LongStageKeyImpl(KeyFunction<K> keyFunction, LongBinaryOperator addition, LongUnaryOperator inverse, long neutral, long def, long offset) {
        super(keyFunction);
        this.addition = addition;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected LongStatsKey<K> createKey(int season, int stage) {
        return new LongStatsKeyImpl<>(this.createKeyFunction(season, stage), this.addition, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected LongStatsKey<K> createKey(int season) {
        return new LongStatsKeyImpl<>(this.createKeyFunction(season), this.addition, this.inverse, this.neutral, this.def, this.offset);
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
}
