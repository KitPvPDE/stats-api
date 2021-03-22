package net.kitpvp.stats.keys;

import java.util.function.*;

class NumericSeasonKeyImpl<K, V> extends SeasonKeyImpl<K, V, NumericStatsKey<K, V>> implements NumericSeasonKey<K, V> {

    private final BinaryOperator<V> sumFunction;
    private final UnaryOperator<V> inverse;
    private final V neutral, def, offset;

    NumericSeasonKeyImpl(KeyFunction<K> keyFunction, BinaryOperator<V> sumFunction, UnaryOperator<V> inverse, V neutral, V def, V offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public NumericVoidSeasonKey<V> bind(K k) {
        return new NumericVoidSeasonKeyImpl<>(this.keyFunction().bind(k), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected NumericStatsKey<K, V> createKey(KeyFunction<K> function) {
        return new NumericStatsKeyImpl<>(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
