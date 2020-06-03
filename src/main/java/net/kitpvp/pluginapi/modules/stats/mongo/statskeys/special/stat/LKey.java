package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.stat;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.ListKey;
import org.bson.Document;

import java.util.List;

public class LKey<K, V> implements ListKey<K, V, List<V>> {

    private final StatsKey<K, List<V>> statsKey;

    public LKey(StatsKey<K, List<V>> statsKey) {
        this.statsKey = statsKey;
    }

    @Override
    public List<V> getDefault() {
        return this.statsKey.getDefault();
    }

    @Override
    public Document append(Document to, K k, List<V> vs) {
        return this.statsKey.append(to, k, vs);
    }

    @Override
    public String getKey(K k) {
        return this.statsKey.getKey(k);
    }

    @Override
    public List<V> apply(List<V> vs) {
        return this.statsKey.apply(vs);
    }
}
