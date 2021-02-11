package net.kitpvp.stats;

import net.kitpvp.stats.api.keys.Entry;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.converter.Converter;
import net.kitpvp.stats.converter.Decoder;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.kitpvp.stats.api.keys.Entry.entry;

public interface StatsReader extends IntReader, LongReader, DoubleReader, BooleanReader, ListReader, SetReader, KeysReader, KeyReader {

    /*

    Syntax:

    statsReader.map(converter)

    CosmeticType -> Enum
    Cosmetic -> interface

    this.cosmetics = statsReader.getKeys(Stats.COSMETIC)
    -> Set<CosmeticType>

    BiFunction<StatsReader, CosmeticType, Cosmetic> function = statsReader -> null;
    this.cosmetics = statsReader.getStatKeys(Stats.COSMETIC) check
    -> Set<Cosmetic>

    this.cosmetics = statsReader.getStatEntries(Stats.COSMETIC)
    -> Set<Entry<CosmeticType, Cosmetic>>

     */

    <V> V find(String key, V def);

    <K> Optional<StatsReader> getStatReader(Key<K> statsKey, K key);

    <K> Set<K> getKeys(Key<K> statsKey);

    <K> Set<StatsReader> getStatReaders(Key<K> statsKey);

    <K> Set<Entry<K, StatsReader>> getStatEntries(Key<K> statsKey);

    default <U> U map(Decoder<U> decoder) {
        return decoder.decode(this);
    }

    <K> List<StatsReader> getStatKeys(Key<K> statsKey, K key);

    default <K, V> List<V> getStatKeys(StatsKey<K, V> statsKey) {
        return this.getKeys(statsKey).stream().map(key -> this.getStatKey(statsKey, key)).collect(Collectors.toList());
    }

    default <K, U> List<U> getStatKeysAndMap(Key<K> statsKey, K key, Decoder<U> decoder) {
        return this.getStatKeys(statsKey, key).stream().map(decoder::decode).collect(Collectors.toList());
    }

    default <K, V, U> List<U> getStatKeysAndMap(StatsKey<K, V> statsKey, Function<V, U> function) {
        return this.getKeys(statsKey).stream().map(key -> this.getStatKey(statsKey, key)).map(function).collect(Collectors.toList());
    }

    default <K, U> Set<Entry<K, U>> getStatEntriesAndMap(Key<K> statsKey, Decoder<U> decoder) {
        return this.getStatEntries(statsKey).stream().map(entry -> entry(entry.getKey(), entry.getValue().map(decoder))).collect(Collectors.toSet());
    }

    default <K, U> U getStatReaderAndMap(Key<K> statsKey, K key, Decoder<U> decoder) {
        return this.getStatReader(statsKey, key).map(decoder::decode).orElse(null);
    }

    default <K, U, W extends StatsWriter> Set<U> getStatReadersAndMap(Key<K> statsKey, Converter<U, W> converter) {
        return this.getStatReaders(statsKey).stream().map(converter::decode).collect(Collectors.toSet());
    }
}
