package net.kitpvp.stats.keys;

import java.util.function.BiFunction;

class BooleanSeasonKeyImpl<K> extends SeasonKeyImpl<K, Boolean, BooleanStatsKey<K>> implements BooleanSeasonKey<K> {

    private final boolean def;

    BooleanSeasonKeyImpl(KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, boolean def) {
        super(keyFunction, seasonKeyMapping);
        this.def = def;
    }

    @Override
    public BooleanVoidSeasonKey bind(K k) {
        return new BooleanVoidSeasonKeyImpl(this.keyFunction.bind(k), this.def);
    }

    @Override
    public BooleanStatsKey<K> season(int season) {
        return super.season(season);
    }

    @Override
    protected BooleanStatsKey<K> createKey(KeyFunction<K> function) {
        return new BooleanStatsKeyImpl<>(function, this.def);
    }
}
