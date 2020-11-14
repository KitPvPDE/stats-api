package net.kitpvp.stats;

import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.IntReader;
import net.kitpvp.stats.reader.LongReader;
import org.bson.Document;

import java.util.Set;
import java.util.function.Function;

public interface StatsReader extends IntReader, LongReader {

    <V> V find(String key, V def);

    <K, V, U> Set<U> getStatKeys(StatsKey<K, V> statsKey, K k, Function<String, U> function);

    default <K, V> V readStatKey(StatsKey<K, V> statsKey, K k) {
        return this.find(statsKey.key(k), statsKey.def());
    }

    default <K, V> V getStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.apply(this.find(statsKey.key(k), statsKey.def()));
    }

    default <U, K, V> U getStatKey(StatsKey<K, V> statsKey, K k, Function<V, U> function) {
        return function.apply(statsKey.apply(this.find(statsKey.key(k), statsKey.def())));
    }

    default <V> V getStatKey(SStatsKey<V> statsKey) {
        return statsKey.apply(this.find(statsKey.key(null), statsKey.def()));
    }

    default <V, U> U getStatKey(SStatsKey<V> statsKey, Function<V, U> function) {
        return function.apply(statsKey.apply(this.find(statsKey.key(null), statsKey.def())));
    }

    default <K, V> Set<String> getStatKeys(StatsKey<K, V> statsKey, K k) {
        return this.getStatKeys(statsKey, k, Function.identity());
    }
}
