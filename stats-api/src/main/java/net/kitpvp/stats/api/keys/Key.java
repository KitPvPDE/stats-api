package net.kitpvp.stats.api.keys;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.builder.keys.KeyBuilder;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.Reader;

import java.util.function.Function;

public interface Key<K> {

    static <K> KeyBuilder<K> builder() {
        return new KeyBuilder<>();
    }

    static <K> Key<K> key(Function<K, String> function) {
        return Key.<K>builder().function(function).buildKey();
    }

    static Key<String> identity() {
        return StatsKey.STRING_KEY;
    }

    KeyFunction<K> keyFunction();

    default String key(K key) {
        return this.keyFunction().key(key);
    }

    default boolean contains(Reader reader, K key) {
        return reader.find(this.key(key), null) != null;
    }

}
