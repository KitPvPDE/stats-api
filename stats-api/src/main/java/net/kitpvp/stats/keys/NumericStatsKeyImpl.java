package net.kitpvp.stats.keys;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

class NumericStatsKeyImpl<K, V> implements NumericStatsKey<K, V> {

    private final KeyFunction<K> keyFunction;
    private final BinaryOperator<V> addition;
    private final UnaryOperator<V> reverse;
    private final V neutral, def, offset;

    NumericStatsKeyImpl(KeyFunction<K> toKey, BinaryOperator<V> addition, UnaryOperator<V> inverse, V neutral, V def, V offset) {
        this.keyFunction = toKey;
        this.addition = addition;
        this.reverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public NumericVoidStatsKey<V> bind(K k) {
        return new NumericVoidStatsKeyImpl<>(KeyFunctions.bind(keyFunction(), k), addition(), inverse(), neutral(), def(), offset());
    }

    @Override
    public KeyFunction<K> keyFunction() {
        return this.keyFunction;
    }

    @Override
    public String key(K k) {
        return this.keyFunction.key(k);
    }

    @Override
    public V apply(V v) {
        return this.addition.apply(this.offset, v);
    }

    @Override
    public V neutral() {
        return this.neutral;
    }

    @Override
    public V def() {
        return this.def;
    }

    @Override
    public V offset() {
        return this.offset;
    }

    @Override
    public BinaryOperator<V> addition() {
        return this.addition;
    }

    @Override
    public UnaryOperator<V> inverse() {
        return this.reverse;
    }
}
