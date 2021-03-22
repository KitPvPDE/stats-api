package net.kitpvp.stats.keys;

import java.util.List;
import java.util.function.Supplier;

class ArrayStatsKeyImpl<K, X> extends StatsKeyImpl<K, List<X>> implements ArrayStatsKey<K, X> {

    ArrayStatsKeyImpl(Supplier<List<X>> def, KeyFunction<K> key) {
        super(def, key);
    }

    @Override
    public ArrayVoidStatsKey<X> bind(K k) {
        return new ArrayVoidStatsKeyImpl<>(this.def, this.keyFunction().bind(k));
    }
}
