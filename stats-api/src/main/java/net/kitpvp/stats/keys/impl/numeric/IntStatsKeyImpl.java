package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.NumericStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.IntStatsKey;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class IntStatsKeyImpl<K> extends NumericStatsKeyImpl<K, Integer> implements IntStatsKey<K> {

    private final IntBinaryOperator function;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    public IntStatsKeyImpl(Function<K, String> keyFunction, IntBinaryOperator function, IntUnaryOperator inverse, int neutral, int def, int offset) {
        super(keyFunction, function::applyAsInt, inverse::applyAsInt, neutral, def, offset);
        this.function = function;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public int applyInt(int i) {
        return i + this.offset;
    }

    @Override
    public int defInt() {
        return this.def;
    }

    @Override
    public int neutralInt() {
        return this.neutral;
    }

    @Override
    public int offsetInt() {
        return this.offset;
    }

    @Override
    public IntBinaryOperator additionInt() {
        return this.function;
    }

    @Override
    public IntUnaryOperator inverseInt() {
        return this.inverse;
    }
}
