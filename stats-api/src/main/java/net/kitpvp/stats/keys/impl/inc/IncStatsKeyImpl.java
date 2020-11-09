package net.kitpvp.stats.keys.impl.inc;

import net.kitpvp.stats.keys.IncStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class IncStatsKeyImpl<K, V> implements IncStatsKey<K, V> {

    private final Function<K, String> key;
    private final BinaryOperator<V> function;
    private final V neutral, offset;

    public IncStatsKeyImpl(Function<K, String> toKey, BinaryOperator<V> function, V neutral, V offset) {
        this.key = toKey;
        this.function = function;
        this.neutral = neutral;
        this.offset = offset;
    }

    @Override
    public String key(K k) {
        return this.key.apply(k);
    }

    @Override
    public V apply(V v) {
        return this.function.apply(this.offset, v);
    }

    @Override
    public V neutral() {
        return this.neutral;
    }

    @Override
    public V def() {
        return this.neutral();
    }

    @Override
    public BinaryOperator<V> function() {
        return this.function;
    }
}
