package net.kitpvp.stats.keys.impl.set;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.impl.VoidStatsKeyImpl;
import net.kitpvp.stats.keys.set.SetSStatsKey;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

public class SetVoidStatsKeyImpl<X> extends VoidStatsKeyImpl<Set<X>> implements SetSStatsKey<X> {

    public SetVoidStatsKeyImpl(Supplier<Set<X>> def, KeyFunction<Void> key) {
        super(def, key);
    }
}
