package net.kitpvp.stats;

import net.kitpvp.stats.converter.Converter;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.*;

public interface StatsReader extends IntReader, LongReader, DoubleReader, BooleanReader, ListReader, SetReader, KeysReader, KeyReader, MappingReader {

    <V> V find(String key, V def);

    <K, V> StatsReader mapStatsReader(StatsKey<K, V> statsKey, K key);

    default <K, V, U> U mapStatsReader(StatsKey<K, V> statsKey, K key, Converter<U> converter) {
        return converter.decode(this.mapStatsReader(statsKey, key));
    }

    default <T> T mapStatsReader(Converter<T> converter) {
        return converter.decode(this);
    }
}
