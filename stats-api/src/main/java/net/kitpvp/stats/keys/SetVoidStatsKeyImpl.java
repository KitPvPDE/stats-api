package net.kitpvp.stats.keys;

import java.util.Set;
import java.util.function.Supplier;

class SetVoidStatsKeyImpl<X> extends VoidStatsKeyImpl<Set<X>> implements SetVoidStatsKey<X> {

    SetVoidStatsKeyImpl(Supplier<Set<X>> def, VoidKeyFunction key) {
        super(def, key);
    }
}
