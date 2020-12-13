package net.kitpvp.stats.keys.impl.bool;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.bool.BooleanStatsKey;
import net.kitpvp.stats.keys.impl.NumericStatsKeyImpl;
import net.kitpvp.stats.keys.impl.StatsKeyImpl;
import net.kitpvp.stats.keys.numeric.IntStatsKey;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class BooleanStatsKeyImpl<K> extends StatsKeyImpl<K, Boolean> implements BooleanStatsKey<K> {

    private final boolean def;

    public BooleanStatsKeyImpl(KeyFunction<K> keyFunction, boolean def) {
        super(null, keyFunction);
        this.def = def;
    }

    @Override
    public boolean applyBoolean(boolean b) {
        return b;
    }

    @Override
    public Boolean def() {
        return this.def;
    }

    @Override
    public boolean defBoolean() {
        return this.def;
    }
}
