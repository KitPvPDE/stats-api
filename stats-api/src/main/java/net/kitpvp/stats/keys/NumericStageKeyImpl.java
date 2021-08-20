package net.kitpvp.stats.keys;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

class NumericStageKeyImpl<K, V> extends StageKeyImpl<K, V, NumericStatsKey<K, V>> implements NumericStageKey<K, V> {

    private final BinaryOperator<V> sumFunction;
    private final UnaryOperator<V> inverse;
    private final V neutral, def, offset;

    NumericStageKeyImpl(KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, UnaryOperator<KeyFunction<K>> remapFunction, BinaryOperator<V> sumFunction, UnaryOperator<V> inverse, V neutral, V def, V offset) {
        super(keyFunction, seasonKeyMapping, remapFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public NumericVoidStageKey<V> bind(K k) {
        return new NumericVoidStageKeyImpl<>(this.keyFunction.bind(k),
                function -> this.remapFunction.apply(this.keyFunction).bind(k),
                this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected NumericStatsKey<K, V> createKey(KeyFunction<K> function) {
        return new NumericStatsKeyImpl<>(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
