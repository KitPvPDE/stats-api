package net.kitpvp.stats.keys;

import java.util.Set;
import java.util.function.Supplier;

public class SetStatsKeyImpl<K, X> extends StatsKeyImpl<K, Set<X>> implements SetStatsKey<K, X> {

    SetStatsKeyImpl(Supplier<Set<X>> def, KeyFunction<K> key) {
        super(def, key);
    }
}
