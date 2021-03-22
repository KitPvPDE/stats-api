package net.kitpvp.stats;

import net.kitpvp.stats.keys.VoidStatsKey;
import net.kitpvp.stats.keys.StatsKey;

// TODO do we really wanna be so strict about writing here ?
public interface StatsWriter {

    <T> void write(String key, T value);

    default <K, V> void setStatKey(StatsKey<K, V> statKey, K key, V value) {
        this.write(statKey.key(key), value);
    }

    default <V> void setStatKey(VoidStatsKey<V> statKey, V value) {
        this.setStatKey(statKey, null, value);
    }

    default <K, U> void setStatKey(Key<K> statKey, K key, U value) {
        this.write(statKey.key(key), value);
    }

    default <U> void setStatKey(VoidKey statKey, U value) {
        this.setStatKey(statKey, null, value);
    }

    default <K> void unsetStatKey(Key<K> statKey, K key) {
        this.write(statKey.key(key), null);
    }

    default <K, V> void unsetStatKey(Key<K> statKey, K key, V ignored) {
        this.unsetStatKey(statKey, key);
    }

    default <K, V> StatsWriter appendStatKey(StatsKey<K, V> statKey, K key, V value) {
        this.setStatKey(statKey, key, value);
        return this;
    }

    default <V> StatsWriter appendStatKey(VoidStatsKey<V> statKey, V value) {
        this.setStatKey(statKey, value);
        return this;
    }

    default <K, U> StatsWriter appendStatKey(Key<K> statKey, K key, U value) {
        this.setStatKey(statKey, key, value);
        return this;
    }

    default <U> StatsWriter appendStatKey(VoidKey statKey, U value) {
        this.setStatKey(statKey, value);
        return this;
    }
}
