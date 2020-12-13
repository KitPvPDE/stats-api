package net.kitpvp.stats.keys.impl.bool;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.bool.BooleanSSeasonKey;
import net.kitpvp.stats.keys.bool.BooleanSStatsKey;
import net.kitpvp.stats.keys.impl.VoidSeasonKeyImpl;

public class BooleanVoidSeasonKeyImpl extends VoidSeasonKeyImpl<Boolean, BooleanSStatsKey> implements BooleanSSeasonKey {

    private final boolean def;

    public BooleanVoidSeasonKeyImpl(KeyFunction<Void> keyFunction, boolean def) {
        super(keyFunction);
        this.def = def;
    }

    @Override
    protected BooleanSStatsKey createKey(int season) {
        return new BooleanVoidStatsKeyImpl(this.createKeyFunction(season), this.def);
    }
}
