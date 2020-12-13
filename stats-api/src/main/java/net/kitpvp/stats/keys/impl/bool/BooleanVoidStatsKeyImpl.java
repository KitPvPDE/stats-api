package net.kitpvp.stats.keys.impl.bool;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.bool.BooleanSStatsKey;
import net.kitpvp.stats.keys.impl.NumericVoidStatsKeyImpl;
import net.kitpvp.stats.keys.impl.VoidStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.IntSStatsKey;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class BooleanVoidStatsKeyImpl extends VoidStatsKeyImpl<Boolean> implements BooleanSStatsKey {

    private final boolean def;

    public BooleanVoidStatsKeyImpl(KeyFunction<Void> keyFunction, boolean def) {
        super(null, keyFunction);
        this.def = def;
    }

    @Override
    public boolean applyBoolean(boolean b) {
        return b;
    }

    @Override
    public boolean defBoolean() {
        return this.def;
    }

    @Override
    public Boolean def() {
        return this.def;
    }
}
