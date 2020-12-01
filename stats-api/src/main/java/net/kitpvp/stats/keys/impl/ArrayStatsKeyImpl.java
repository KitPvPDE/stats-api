package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.array.ArrayStatsKey;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayStatsKeyImpl<K, X> extends StatsKeyImpl<K, List<X>> implements ArrayStatsKey<K, X> {

    public ArrayStatsKeyImpl(Supplier<List<X>> def, KeyFunction<K> key) {
        super(def, key);
    }
}
