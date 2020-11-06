package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.keys.StatsKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

public class StatsKeyImpl<K, V> implements StatsKey<K, V> {

    private final Supplier<V> def;
    private final Function<K, String> key;

    public StatsKeyImpl(Supplier<V> def, Function<K, String> key) {
        this.def = def;
        this.key = key;
    }

    @Override
    public V apply(V v) {
        return v;
    }

    @Override
    public String key(K k) {
        return this.key.apply(k);
    }

    public V def() {
        return this.def.get();
    }

}
