package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.functions.TriConsumer;
import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.builder.Builder;

public interface StatsKey<K, V> extends AppendableKey<K, V> {

    static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    V def();

    String key(K k);

    V apply(V v);

    @Override
    default void append(K key, V value, TriConsumer<StatsKey<K, V>, K, V> function) {
        function.accept(this, key, value);
    }

}
