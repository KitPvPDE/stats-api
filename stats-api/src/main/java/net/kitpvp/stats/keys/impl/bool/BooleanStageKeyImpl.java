package net.kitpvp.stats.keys.impl.bool;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.bool.BooleanStageKey;
import net.kitpvp.stats.keys.bool.BooleanStatsKey;
import net.kitpvp.stats.keys.impl.StageKeyImpl;

public class BooleanStageKeyImpl<K> extends StageKeyImpl<K, Boolean, BooleanStatsKey<K>> implements BooleanStageKey<K> {

    private final boolean def;

    public BooleanStageKeyImpl(KeyFunction<K> keyFunction, boolean def) {
        super(keyFunction);
        this.def = def;
    }

    @Override
    protected BooleanStatsKey<K> createKey(int season, int stage) {
        return new BooleanStatsKeyImpl<>(this.createKeyFunction(season, stage), this.def);
    }

    @Override
    protected BooleanStatsKey<K> createKey(int season) {
        return new BooleanStatsKeyImpl<>(this.createKeyFunction(season), this.def);
    }
}
