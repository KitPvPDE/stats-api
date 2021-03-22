package net.kitpvp.stats.keys;

import java.util.List;
import java.util.function.Supplier;

class ArrayVoidStatsKeyImpl<X> extends VoidStatsKeyImpl<List<X>> implements ArrayVoidStatsKey<X> {

    ArrayVoidStatsKeyImpl(Supplier<List<X>> def, VoidKeyFunction key) {
        super(def, key);
    }
}
