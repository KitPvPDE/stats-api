package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.array.ArraySStatsKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface ListReader extends Reader {

    default <K, X> List<X> getListKey(ArrayStatsKey<K, X> statsKey, K key) {
        return this.getStatKey(statsKey, key);
    }

    default <X> List<X> getListKey(ArraySStatsKey<X> statsKey) {
        return this.getStatKey(statsKey, null);
    }

    default <K, X, U> List<U> getListKey(ArrayStatsKey<K, X> statsKey, K key, Function<X, U> function) {
        return this.getListKey(statsKey, key).stream().map(function).collect(Collectors.toList());
    }

    default <X, U> List<U> getListKey(ArraySStatsKey<X> statsKey, Function<X, U> function) {
        return this.getListKey(statsKey).stream().map(function).collect(Collectors.toList());
    }

}
