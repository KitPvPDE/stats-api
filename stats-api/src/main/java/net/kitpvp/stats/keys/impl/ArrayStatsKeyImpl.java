package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.keys.array.ArrayStatsKey;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayStatsKeyImpl<K, X> extends StatsKeyImpl<K, List<X>> implements ArrayStatsKey<K, X> {

    public ArrayStatsKeyImpl(Supplier<List<X>> def, Function<K, String> key) {
        super(def, key);
    }
}
