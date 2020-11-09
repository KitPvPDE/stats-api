package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.numeric.LongSStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.LongBinaryOperator;

public class LongVoidStatsKeyImpl implements LongSStatsKey {

    private final Function<Void, String> keyFunction;
    private final LongBinaryOperator function;
    private final long neutral, def, offset;

    public LongVoidStatsKeyImpl(Function<Void, String> keyFunction, LongBinaryOperator function, long neutral, long def, long offset) {
        this.keyFunction = keyFunction;
        this.function = function;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public String key(Void v) {
        return this.keyFunction.apply(v);
    }

    @Override
    public Long apply(Long l) {
        return this.applyLong(l);
    }

    @Override
    public long applyLong(long i) {
        return i + this.offset;
    }

    @Override
    public Long def() {
        return this.def;
    }

    @Override
    public long defLong() {
        return this.def;
    }

    @Override
    public Long neutral() {
        return this.neutral;
    }

    @Override
    public long neutralLong() {
        return this.neutral;
    }

    @Override
    public BinaryOperator<Long> function() {
        return this.function::applyAsLong;
    }

    @Override
    public LongBinaryOperator longFunction() {
        return this.function;
    }

}
