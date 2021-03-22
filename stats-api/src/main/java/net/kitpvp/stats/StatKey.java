package net.kitpvp.stats;

import net.kitpvp.stats.api.functions.TriConsumer;
import net.kitpvp.stats.keys.StatsKey;

public interface StatKey<K, V> {

    void forEach(K key, V value, TriConsumer<StatsKey<K, V>, K, V> callback);
}
