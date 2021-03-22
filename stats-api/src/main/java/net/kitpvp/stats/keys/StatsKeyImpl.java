package net.kitpvp.stats.keys;

import java.util.function.Supplier;

class StatsKeyImpl<K, V> implements StatsKey<K, V> {

    protected final Supplier<V> def;
    protected final KeyFunction<K> key;

    StatsKeyImpl(Supplier<V> def, KeyFunction<K> key) {
        this.def = def;
        this.key = key;
    }

    @Override
    public VoidStatsKey<V> bind(K k) {
        return new VoidStatsKeyImpl<>(this.def, KeyFunctions.bind(this.keyFunction(), k));
    }

    @Override
    public KeyFunction<K> keyFunction() {
        return this.key;
    }

    @Override
    public String key(K k) {
        return this.key.key(k);
    }

    @Override
    public V apply(V v) {
        return v;
    }

    public V def() {
        return this.def.get();
    }
}
