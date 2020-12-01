package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.array.ArraySStatsKey;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayVoidStatsKeyImpl<X> extends VoidStatsKeyImpl<List<X>> implements ArraySStatsKey<X> {

    public ArrayVoidStatsKeyImpl(Supplier<List<X>> def, KeyFunction<Void> key) {
        super(def, key);
    }
}
