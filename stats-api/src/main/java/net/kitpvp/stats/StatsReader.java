package net.kitpvp.stats;

import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.*;
import org.bson.Document;

import java.util.Set;
import java.util.function.Function;

public interface StatsReader extends IntReader, LongReader, DoubleReader, ListReader, SetReader {

    <V> V find(String key, V def);

    <K, V, U> Set<U> getStatKeys(StatsKey<K, V> statsKey, Function<String, U> function);

    default <K, V, U> Set<U> getStatKeys(StatsKey<K, V> statsKey, K k, Function<String, U> function) {
        return this.getStatKeys(statsKey, function);
    }

    default <K, V> Set<String> getStatKeys(StatsKey<K, V> statsKey) {
        return this.getStatKeys(statsKey, Function.identity());
    }

    default <K, V> Set<String> getStatKeys(StatsKey<K, V> statsKey, K k) {
        return this.getStatKeys(statsKey, Function.identity());
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

    default <V> V getStatKey(SStatsKey<V> statsKey) {
        return statsKey.apply(statsKey.extract(this, null));
    }

    default <V, U> U getStatKey(SStatsKey<V> statsKey, Function<V, U> function) {
        return function.apply(statsKey.apply(statsKey.extract(this, null)));
    }
}
