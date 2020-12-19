package net.kitpvp.stats.keys.impl.bool;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.keys.bool.BooleanSStageKey;
import net.kitpvp.stats.keys.bool.BooleanSStatsKey;
import net.kitpvp.stats.keys.impl.VoidStageKeyImpl;

public class BooleanVoidStageKeyImpl extends VoidStageKeyImpl<Boolean, BooleanSStatsKey> implements BooleanSStageKey {

    private final boolean def;

    public BooleanVoidStageKeyImpl(VoidKeyFunction keyFunction, boolean def) {
        super(keyFunction);
        this.def = def;
    }

    @Override
    protected BooleanSStatsKey createKey(int season, int stage) {
        return new BooleanVoidStatsKeyImpl(this.createKeyFunction(season, stage), this.def);
    }

    @Override
    protected BooleanSStatsKey createKey(int season) {
        return new BooleanVoidStatsKeyImpl(this.createKeyFunction(season), this.def);
    }
}
