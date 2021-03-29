package net.kitpvp.stats.keys;

import net.kitpvp.stats.reader.Reader;

import java.util.stream.Stream;

public interface BooleanStatsKey<K> extends StatsKey<K, Boolean>, IterableBooleanKey<K> {

    static <K> BooleanKeyBuilder<K> builder() {
        return new BooleanKeyBuilder<>();
    }

    static BooleanStatsKey<String> identity() {
        return BooleanStatsKeyImpl.IDENTITY;
    }

    @Override
    @Deprecated
    Boolean def();

    boolean defBoolean();

    @Override
    Boolean apply(Boolean integer);

    boolean applyBoolean(boolean b);

    @Override
    String key(K k);

    @Override
    KeyFunction<K> keyFunction();

    @Override
    VoidStatsKey<Boolean> bind(K k);

    @Override
    default Stream<? extends BooleanStatsKey<K>> stream() {
        return Stream.of(this);
    }

    @Override
    default Boolean extract(Reader statsReader, K key) {
        return this.extractBoolean(statsReader, key);
    }

    default boolean extractBoolean(Reader statsReader, K key) {
        return statsReader.find(this.key(key), this.defBoolean());
    }
}
