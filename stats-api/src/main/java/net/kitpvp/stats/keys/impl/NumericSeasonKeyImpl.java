package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.keys.IncSeasonKey;
import net.kitpvp.stats.keys.IncStatsKey;

import java.util.function.*;

public class NumericSeasonKeyImpl<K, V> extends SeasonKeyImpl<K, V, IncStatsKey<K, V>> implements IncSeasonKey<K, V> {

    private final BinaryOperator<V> sumFunction;
    private final UnaryOperator<V> inverse;
    private final V neutral, def, offset;

    public NumericSeasonKeyImpl(Function<K, String> keyFunction, BinaryOperator<V> sumFunction, UnaryOperator<V> inverse, V neutral, V def, V offset) {
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
}
