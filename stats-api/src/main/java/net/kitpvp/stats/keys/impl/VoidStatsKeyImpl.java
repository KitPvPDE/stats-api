package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.keys.SStatsKey;

import java.util.function.Function;
import java.util.function.Supplier;

public class VoidStatsKeyImpl<V> implements SStatsKey<V> {

    private final Supplier<V> def;
    private final Function<Void, String> key;

    public VoidStatsKeyImpl(Supplier<V> def, Function<Void, String> key) {
        this.def = def;
        this.key = key;
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
