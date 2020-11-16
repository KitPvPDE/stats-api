package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.set.SetSStatsKey;
import net.kitpvp.stats.keys.set.SetStatsKey;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface SetReader extends Reader {

    default <K, X> Set<X> getSetKey(SetStatsKey<K, X> statsKey, K key) {
        return this.getStatKey(statsKey, key);
    }

    default <X> Set<X> getSetKey(SetSStatsKey<X> statsKey) {
        return this.getStatKey(statsKey, null);
    }

    default <K, X, U> Set<U> getSetKey(SetStatsKey<K, X> statsKey, K key, Function<X, U> function) {
        return this.getSetKey(statsKey, key).stream().map(function).collect(Collectors.toSet());
    }

    default <X, U> Set<U> getSetKey(SetSStatsKey<X> statsKey, Function<X, U> function) {
        return this.getSetKey(statsKey).stream().map(function).collect(Collectors.toSet());
    }

}
