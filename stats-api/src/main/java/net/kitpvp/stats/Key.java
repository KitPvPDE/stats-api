package net.kitpvp.stats;

import net.kitpvp.stats.keys.IterableKey;
import net.kitpvp.stats.keys.KeyFunction;
import net.kitpvp.stats.keys.KeyBuilder;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.Reader;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Key<K> extends IterableKey<K> {

    static <K> KeyBuilder<K> builder() {
        return KeyBuilder.builder();
    }

    static <K> Key<K> key(Function<K, String> function) {
        return Key.<K>builder().function(function).buildKey();
    }

    static Key<String> identity() {
        return StatsKey.identity();
    }

    KeyFunction<K> keyFunction();

    default String key(K key) {
        return this.keyFunction().key(key);
    }

    default boolean contains(Reader reader, K key) {
        return reader.find(this.key(key), null) != null;
    }
}
