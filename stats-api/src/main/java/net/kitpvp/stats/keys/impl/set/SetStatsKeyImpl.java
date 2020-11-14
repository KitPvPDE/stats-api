package net.kitpvp.stats.keys.impl.set;

import net.kitpvp.stats.keys.impl.StatsKeyImpl;
import net.kitpvp.stats.keys.set.SetStatsKey;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class SetStatsKeyImpl<K, X> extends StatsKeyImpl<K, Set<X>> implements SetStatsKey<K, X> {

    public SetStatsKeyImpl(Supplier<Set<X>> def, Function<K, String> key) {
        super(def, key);
    }
}
