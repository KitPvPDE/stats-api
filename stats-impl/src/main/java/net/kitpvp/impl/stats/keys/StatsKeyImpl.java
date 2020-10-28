package net.kitpvp.impl.stats.keys;

import net.kitpvp.api.stats.keys.StatsKey;

import java.util.function.Function;
import java.util.function.Supplier;

public class StatsKeyImpl<K, V> implements StatsKey<K, V> {

    private final Supplier<V> toDefault;
    private final Function<K, String> toKey;
    private final Function<V, V> toValue;

    public StatsKeyImpl(Supplier<V> toDefault, Function<K, String> toKey, Function<V, V> toValue) {
        this.toDefault = toDefault;
        this.toKey = toKey;
        this.toValue = toValue;
    }

    @Override
    public V apply(V v) {
        if(this.toValue != null)
            return this.toValue.apply(v);

        return v;
    }

    @Override
    public String getKey(K k) {
        return this.toKey.apply(k);
    }

    @Override
    public Document append(Document to, K k, V v) {
        return to.append(this.getKey(k), v);
    }

    public V getDefault() {
        return this.toDefault.get();
    }

}
