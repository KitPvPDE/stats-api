package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.numeric.IntStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class IntStatsKeyImpl<K> implements IntStatsKey<K> {

    private final Function<K, String> keyFunction;
    private final IntBinaryOperator function;
    private final int neutral, def, offset;

    public IntStatsKeyImpl(Function<K, String> keyFunction, IntBinaryOperator function, int neutral, int def, int offset) {
        this.keyFunction = keyFunction;
        this.function = function;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public String key(K k) {
        return this.keyFunction.apply(k);
    }

    @Override
    public Integer apply(Integer integer) {
        return this.applyInt(integer);
    }

    @Override
    public int applyInt(int i) {
        return i + this.offset;
    }

    @Override
    public Integer def() {
        return this.def;
    }

    @Override
    public int defInt() {
        return this.def;
    }

    @Override
    public Integer neutral() {
        return this.neutral;
    }

    @Override
    public int neutralInt() {
        return this.neutral;
    }

    @Override
    public BinaryOperator<Integer> function() {
        return this.function::applyAsInt;
    }

    @Override
    public IntBinaryOperator intFunction() {
        return this.function;
    }
}
