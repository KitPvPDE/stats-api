package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.SStatsKey;

import java.util.function.Function;
import java.util.function.Supplier;

public class VoidStatsKeyImpl<V> implements SStatsKey<V> {

    private final Supplier<V> def;
    private final KeyFunction<Void> key;

    public VoidStatsKeyImpl(Supplier<V> def, KeyFunction<Void> key) {
        this.def = def;
        this.key = key;
    }

    @Override
    public KeyFunction<Void> keyFunction() {
        return this.key;
    }

    @Override
    public String key(Void unused) {
        return this.key.apply(null);
    }

    @Override
    public V apply(V v) {
        return v;
    }

    @Override
    public V def() {
        return this.def.get();
    }
}
