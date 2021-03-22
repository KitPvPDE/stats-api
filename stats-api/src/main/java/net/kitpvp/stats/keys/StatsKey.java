package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;
import net.kitpvp.stats.reader.Reader;

import java.util.function.Function;
import java.util.stream.Stream;

public interface StatsKey<K, V> extends Key<K>, IterableStatsKey<K, V> {

    static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    static StatsKey<String, String> identity() {
        return IDENTITY;
    }

    StatsKey<String, String> IDENTITY = StatsKey.<String, String>builder().keyBuilder(builder -> builder.function(Function.identity()).inverse(Function.identity())).build();

    @Deprecated
    Key<String> STRING_KEY = IDENTITY;

    KeyFunction<K> keyFunction();

    String key(K k);

    V def();

    V apply(V v);

    VoidStatsKey<V> bind(K k);

    @Override
    default Stream<? extends StatsKey<K, V>> stream() {
        return Stream.of(this);
    }

    default V extract(Reader statsReader, K key) {
        return statsReader.find(this.key(key), this.def());
    }

    default boolean contains(Reader statsReader, K key) {
        return statsReader.find(this.key(key), null) != null;
    }
}
