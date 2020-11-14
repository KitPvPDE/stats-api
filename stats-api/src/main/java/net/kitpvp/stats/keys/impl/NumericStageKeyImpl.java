package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.keys.IncStageKey;
import net.kitpvp.stats.keys.IncStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class NumericStageKeyImpl<K, V> extends StageKeyImpl<K, V, IncStatsKey<K, V>> implements IncStageKey<K, V> {

    private final BinaryOperator<V> sumFunction;
    private final UnaryOperator<V> inverse;
    private final V neutral, def, offset;

    public NumericStageKeyImpl(Function<K, String> keyFunction, BinaryOperator<V> sumFunction, UnaryOperator<V> inverse, V neutral, V def, V offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected IncStatsKey<K, V> createKey(int season) {
        return new NumericStatsKeyImpl<>(this.createKeyFunction(season), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected IncStatsKey<K, V> createKey(int season, int stage) {
        return new NumericStatsKeyImpl<>(this.createKeyFunction(season, stage), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
