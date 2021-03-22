package net.kitpvp.stats.keys;

class BooleanStageKeyImpl<K> extends StageKeyImpl<K, Boolean, BooleanStatsKey<K>> implements BooleanStageKey<K> {

    private final boolean def;

    BooleanStageKeyImpl(KeyFunction<K> keyFunction, boolean def) {
        super(keyFunction);
        this.def = def;
    }

    @Override
    public BooleanVoidStageKey bind(K k) {
        return new BooleanVoidStageKeyImpl(this.keyFunction.bind(k), this.def);
    }

    @Override
    protected BooleanStatsKey<K> createKey(KeyFunction<K> function) {
        return new BooleanStatsKeyImpl<>(function, this.def);
    }
}
