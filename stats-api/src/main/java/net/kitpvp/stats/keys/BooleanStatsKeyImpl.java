package net.kitpvp.stats.keys;

class BooleanStatsKeyImpl<K> extends StatsKeyImpl<K, Boolean> implements BooleanStatsKey<K> {

    private final boolean def;

    BooleanStatsKeyImpl(KeyFunction<K> keyFunction, boolean def) {
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
