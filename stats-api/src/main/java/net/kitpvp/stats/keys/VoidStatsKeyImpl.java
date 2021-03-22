package net.kitpvp.stats.keys;

import java.util.function.Supplier;

public class VoidStatsKeyImpl<V> implements VoidStatsKey<V> {

    private final Supplier<V> def;
    private final VoidKeyFunction key;

    VoidStatsKeyImpl(Supplier<V> def, VoidKeyFunction key) {
        this.def = def;
        this.key = key;
    }

    @Override
    public VoidKeyFunction keyFunction() {
        return this.key;
    }

    @Override
    public String key(Void unused) {
        return this.key.key(null);
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
