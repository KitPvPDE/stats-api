package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

class IntStatsKeyImpl<K> extends NumericStatsKeyImpl<K, Integer> implements IntStatsKey<K> {

    static final IntStatsKey<String> IDENTITY = IntStatsKey.<String>builder()
            .keyBuilder(builder -> builder.key(Key.identity()))
            .build();

    private final IntBinaryOperator function;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    IntStatsKeyImpl(KeyFunction<K> keyFunction, IntBinaryOperator function, IntUnaryOperator inverse, int neutral, int def, int offset) {
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
