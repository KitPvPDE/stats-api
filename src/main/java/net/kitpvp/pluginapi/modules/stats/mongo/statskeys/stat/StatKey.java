package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import org.bson.Document;

import java.util.function.Function;
import java.util.function.Supplier;

class StatKey<K, V> implements StatsKey<K, V> {

    private final V def;
    private final Supplier<V> toDefault;
    private final Function<K, String> toKey;
    private final Function<V, V> toValue;

    public StatKey(V def, Function<K, String> toKey, Function<V, V> toValue) {
        this.def = def;
        this.toKey = toKey;
        this.toValue = toValue;
        this.toDefault = null;
    }

    public StatKey(Supplier<V> toDefault, Function<K, String> toKey, Function<V, V> toValue) {
        this.def = null;
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
        return this.def != null ? this.def : this.toDefault != null ? this.toDefault.get() : null;
    }
}
