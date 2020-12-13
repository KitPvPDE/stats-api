package net.kitpvp.stats.keys.bool;

import net.kitpvp.stats.builder.bool.BooleanKeyBuilder;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.Reader;

public interface BooleanStatsKey<K> extends StatsKey<K, Boolean> {

    static <K> BooleanKeyBuilder<K> builder() {
        return new BooleanKeyBuilder<>();
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
    default Boolean extract(Reader statsReader, K key) {
        return this.extractBoolean(statsReader, key);
    }

    default boolean extractBoolean(Reader statsReader, K key) {
        return statsReader.find(this.key(key), this.defBoolean());
    }
}
