package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.keys.IncSStageKey;
import net.kitpvp.stats.keys.IncSStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class NumericVoidStageKeyImpl<V> extends VoidStageKeyImpl<V, IncSStatsKey<V>> implements IncSStageKey<V> {

    private final BinaryOperator<V> sumFunction;
    private final UnaryOperator<V> inverse;
    private final V neutral, def, offset;

    public NumericVoidStageKeyImpl(Function<Void, String> keyFunction, BinaryOperator<V> sumFunction, UnaryOperator<V> inverse, V neutral, V def, V offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected IncSStatsKey<V> createKey(int season) {
        return new NumericVoidStatsKeyImpl<>(this.createKeyFunction(season), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected IncSStatsKey<V> createKey(int season, int stage) {
        return new NumericVoidStatsKeyImpl<>(this.createKeyFunction(season, stage), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
