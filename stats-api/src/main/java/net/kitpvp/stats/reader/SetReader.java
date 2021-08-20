package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.SetVoidStatsKey;
import net.kitpvp.stats.keys.SetStatsKey;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface SetReader extends Reader {

    default <X> Set<X> getSetKey(String key, Supplier<Set<X>> def) {
        Set<X> set = this.find(key, null);
        if(set == null)
            set = def.get();
        return set;
    }

    default <K, X> Set<X> getSetKey(SetStatsKey<K, X> statsKey, K key) {
        return statsKey.extract(this, key);
    }

    default <X> Set<X> getSetKey(SetVoidStatsKey<X> statsKey) {
        return this.getSetKey(statsKey, (Void) null);
    }

    default <K, X, U> Set<U> getSetKey(SetStatsKey<K, X> statsKey, K key, Function<X, U> function) {
        return this.getSetKey(statsKey, key).stream().map(function).collect(Collectors.toSet());
    }

    default <X, U> Set<U> getSetKey(SetVoidStatsKey<X> statsKey, Function<X, U> function) {
        return this.getSetKey(statsKey).stream().map(function).collect(Collectors.toSet());
    }

}
