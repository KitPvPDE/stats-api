package net.kitpvp.stats;

import net.kitpvp.stats.keys.StatsKey;

public interface StatsWriter {

    <T> void write(String key, T value);

    default <K, V> void setStatKey(StatsKey<K, V> statKey, K key, V value) {
        this.write(statKey.key(key), value);
    }

    default <K, V> void unsetStatKey(StatsKey<K, V> statKey, K key, V ignored) {
        this.write(statKey.key(key), null);
    }
}
