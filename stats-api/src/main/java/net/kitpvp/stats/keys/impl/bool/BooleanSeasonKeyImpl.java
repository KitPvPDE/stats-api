package net.kitpvp.stats.keys.impl.bool;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.bool.BooleanSeasonKey;
import net.kitpvp.stats.keys.bool.BooleanStatsKey;
import net.kitpvp.stats.keys.impl.SeasonKeyImpl;

public class BooleanSeasonKeyImpl<K> extends SeasonKeyImpl<K, Boolean, BooleanStatsKey<K>> implements BooleanSeasonKey<K> {

    private final boolean def;

    public BooleanSeasonKeyImpl(KeyFunction<K> keyFunction, boolean def) {
        super(keyFunction);
        this.def = def;
    }

    @Override
    public BooleanStatsKey<K> season(int season) {
        return super.season(season);
    }

    @Override
    protected BooleanStatsKey<K> createKey(int season) {
        return new BooleanStatsKeyImpl<>(this.createKeyFunction(season), this.def);
    }
}
