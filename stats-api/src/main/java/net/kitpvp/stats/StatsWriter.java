package net.kitpvp.stats;

import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;

public interface StatsWriter {

    <T> void write(String key, T value);

    default <K, V> void setStatKey(StatsKey<K, V> statKey, K key, V value) {
        this.write(statKey.key(key), value);
    }

    default <V> void setStatKey(SStatsKey<V> statKey, V value) {
        this.setStatKey(statKey, null, value);
    }

    default <K> void unsetStatKey(Key<K> statKey, K key) {
        this.write(statKey.key(key), null);
    }

    default <K, V> void unsetStatKey(Key<K> statKey, K key, V ignored) {
        this.unsetStatKey(statKey, key);
    }
}
