package net.kitpvp.stats.keys;

class BooleanSeasonKeyImpl<K> extends SeasonKeyImpl<K, Boolean, BooleanStatsKey<K>> implements BooleanSeasonKey<K> {

    private final boolean def;

    BooleanSeasonKeyImpl(KeyFunction<K> keyFunction, boolean def) {
        super(keyFunction);
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
