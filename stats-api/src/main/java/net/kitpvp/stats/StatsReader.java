package net.kitpvp.stats;

import net.kitpvp.stats.api.keys.Entry;
import net.kitpvp.stats.converter.Decoder;
import net.kitpvp.stats.keys.*;
import net.kitpvp.stats.keys.ArrayVoidStatsKey;
import net.kitpvp.stats.keys.ArrayStatsKey;
import net.kitpvp.stats.reader.*;
import net.kitpvp.stats.season.Season;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.kitpvp.stats.api.keys.Entry.entry;

public interface StatsReader extends IntReader, LongReader, DoubleReader, BooleanReader, SetReader, Reader {

    <V> V find(String key, V def, Class<V> type);

    default Optional<StatsReader> getStatReader(String key) {
        throw new UnsupportedOperationException();
    }

    default <K> Optional<StatsReader> getStatReader(Key<K> statsKey, K key) {
        throw new UnsupportedOperationException();
    }

    default <K> Set<K> getKeys(Key<K> statsKey) {
        throw new UnsupportedOperationException();
    }

    default <K> Set<StatsReader> getStatReaders(Key<K> statsKey) {
        throw new UnsupportedOperationException();
    }

    default <K> Set<Entry<K, StatsReader>> getStatEntries(Key<K> statsKey) {
        throw new UnsupportedOperationException();
    }

    default <U> U map(Decoder<U> decoder) {
        return decoder.decode(this);
    }

    default <K> List<StatsReader> getStatKeys(Key<K> statsKey, K key) {
        throw new UnsupportedOperationException();
    }

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

    default <K, U> Set<U> getStatReadersAndMap(Key<K> statsKey, Decoder<U> converter) {
        return this.getStatReaders(statsKey).stream().map(converter::decode).collect(Collectors.toSet());
    }

    /*
     *
     * Key methods
     *
     */

    default <V> V getStatKey(String key, V def) {
        return this.find(key, def);
    }

    default <V, U> U getStatKey(String key, V def, Function<V, U> map) {
        return map.apply(getStatKey(key, def));
    }

    default <K, V> V readStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.extract(this, k);
    }

    default <K, V> V getStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.apply(statsKey.extract(this, k));
    }

    default <U, K, V> U getStatKey(StatsKey<K, V> statsKey, K k, Function<V, U> function) {
        return function.apply(statsKey.apply(statsKey.extract(this, k)));
    }

    default <V> V getStatKey(VoidStatsKey<V> statsKey) {
        return statsKey.apply(statsKey.extract(this, null));
    }

    default <V, U> U getStatKey(VoidStatsKey<V> statsKey, Function<V, U> function) {
        return function.apply(statsKey.apply(statsKey.extract(this, null)));
    }

    default boolean hasStatKey(VoidKey statsKey) {
        return statsKey.contains(this, null);
    }

    default <K> boolean hasStatKey(Key<K> statsKey, K key) {
        return statsKey.contains(this, key);
    }

    /*
     *
     * List methods
     *
     */

    default <K, X> List<X> getListKey(ArrayStatsKey<K, X> statsKey, K key) {
        return this.getStatKey(statsKey, key);
    }

    default <X> List<X> getListKey(ArrayVoidStatsKey<X> statsKey) {
        return this.getStatKey(statsKey, (Void) null);
    }

    default <K, X, U> List<U> getListKey(ArrayStatsKey<K, X> statsKey, K key, Function<X, U> function) {
        return this.getListKey(statsKey, key).stream().map(function).collect(Collectors.toList());
    }

    default <X, U> List<U> getListKey(ArrayVoidStatsKey<X> statsKey, Function<X, U> function) {
        return this.getListKey(statsKey).stream().map(function).collect(Collectors.toList());
    }

    /*
     *
     * Boolean Methods
     *
     */

    default boolean getBooleanKey(String key, boolean def) {
        return this.find(key, def, Boolean.class);
    }

    default <K> boolean getBooleanKey(BooleanStatsKey<K> statsKey, K key) {
        return statsKey.applyBoolean(readStatKey(statsKey, key));
    }

    default boolean getBooleanKey(BooleanVoidStatsKey statsKey) {
        return getBooleanKey(statsKey, null);
    }

    default <K> boolean getBooleanKey(BooleanSeasonKey<K> seasonKey, K key, int season) {
        return getBooleanKey(seasonKey.season(season), key);
    }

    default boolean getBooleanKey(BooleanVoidSeasonKey seasonKey, int season) {
        return getBooleanKey(seasonKey.season(season));
    }

    default <K> boolean getAlltimeBooleanKey(BooleanSeasonKey<K> seasonKey, K key) {
        return getBooleanKey(seasonKey, key, ALLTIME);
    }

    default boolean getAlltimeBooleanKey(BooleanVoidSeasonKey seasonKey) {
        return getBooleanKey(seasonKey, null, ALLTIME);
    }

    default <K> boolean getSeasonBooleanKey(BooleanSeasonKey<K> seasonKey, K key) {
        return getBooleanKey(seasonKey, key, Season.getSeason());
    }

    default boolean getSeasonBooleanKey(BooleanVoidSeasonKey seasonKey) {
        return getBooleanKey(seasonKey, null, Season.getSeason());
    }

    default <K> boolean getStageBooleanKey(BooleanStageKey<K> stageKey, K key) {
        return getBooleanKey(stageKey.stage(), key);
    }

    default boolean getStageBooleanKey(BooleanVoidStageKey stageKey) {
        return getBooleanKey(stageKey.stage());
    }

    /*
     *
     * Integer Methods
     *
     */


}
