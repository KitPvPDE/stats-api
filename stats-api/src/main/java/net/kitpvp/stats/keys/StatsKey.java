package net.kitpvp.stats.keys;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.api.functions.TriConsumer;
import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.builder.Builder;
import net.kitpvp.stats.reader.Reader;

import java.util.function.Function;

public interface StatsKey<K, V> extends AppendableKey<K, V> {

    static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    KeyFunction<K> keyFunction();

    String key(K k);

    V def();

    V apply(V v);

    @Override
    default void append(K key, V value, TriConsumer<StatsKey<K, V>, K, V> function) {
        function.accept(this, key, value);
    }

    default V extract(Reader statsReader, K key) {
        return statsReader.find(this.key(key), this.def());
    }

    default boolean contains(Reader statsReader, K key) {
        return statsReader.find(this.key(key), null) != null;
    }
}
