package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.keys.IncSSeasonKey;
import net.kitpvp.stats.keys.IncSStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class NumericVoidSeasonKeyImpl<V> extends VoidSeasonKeyImpl<V, IncSStatsKey<V>> implements IncSSeasonKey<V> {

    private final BinaryOperator<V> sumFunction;
    private final UnaryOperator<V> inverse;
    private final V neutral, def, offset;

    public NumericVoidSeasonKeyImpl(VoidKeyFunction keyFunction, BinaryOperator<V> sumFunction, UnaryOperator<V> inverse, V neutral, V def, V offset) {
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
}
