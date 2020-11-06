package net.kitpvp.stats.api.keys;

import net.kitpvp.stats.api.functions.TriConsumer;
import net.kitpvp.stats.keys.StatsKey;

public interface AppendableKey<K, V> {

    void append(K key, V value, TriConsumer<StatsKey<K, V>, K, V> function);

}
