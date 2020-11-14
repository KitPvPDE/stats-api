package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.keys.array.ArraySStatsKey;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayVoidStatsKeyImpl<X> extends VoidStatsKeyImpl<List<X>> implements ArraySStatsKey<X> {

    public ArrayVoidStatsKeyImpl(Supplier<List<X>> def, Function<Void, String> key) {
        super(def, key);
    }
}
