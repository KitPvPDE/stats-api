package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;

import java.util.function.Function;

public interface KeyReader extends Reader {

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
