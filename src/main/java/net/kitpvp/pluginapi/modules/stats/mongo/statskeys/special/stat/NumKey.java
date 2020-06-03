package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.stat;

import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.NumberKey;
import org.bson.Document;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class NumKey<K, V> implements NumberKey<K, V> {

    private final StatsKey<K, V> statsKey;
    private final BiFunction<V, V, V> increment;

    @Override
    public V inc(V v1, V v2) {
        return this.increment.apply(v1, v2);
    }

    @Override
    public V getDefault() {
        return this.statsKey.getDefault();
    }

    @Override
    public Document append(Document to, K k, V v) {
        return this.statsKey.append(to, k, v);
    }

    @Override
    public String getKey(K k) {
        return this.statsKey.getKey(k);
    }

    @Override
    public V apply(V v) {
        return this.statsKey.apply(v);
    }
}
