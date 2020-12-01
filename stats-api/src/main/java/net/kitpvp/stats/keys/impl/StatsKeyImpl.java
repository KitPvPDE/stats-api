package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.StatsKey;

import java.util.function.Supplier;

public class StatsKeyImpl<K, V> implements StatsKey<K, V> {

    private final Supplier<V> def;
    private final KeyFunction<K> key;

    public StatsKeyImpl(Supplier<V> def, KeyFunction<K> key) {
        this.def = def;
        this.key = key;
    }

    @Override
    public KeyFunction<K> keyFunction() {
        return this.key;
    }

    @Override
    public String key(K k) {
        return this.key.apply(k);
    }

    @Override
    public V apply(V v) {
        return v;
    }

    public V def() {
        return this.def.get();
    }

}
