package net.kitpvp.stats.keys.impl.set;

import net.kitpvp.stats.keys.impl.VoidStatsKeyImpl;
import net.kitpvp.stats.keys.set.SetSStatsKey;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class SetVoidStatsKeyImpl<X> extends VoidStatsKeyImpl<Set<X>> implements SetSStatsKey<X> {

    public SetVoidStatsKeyImpl(Supplier<Set<X>> def, Function<Void, String> key) {
        super(def, key);
    }
}
