package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;

class BooleanStatsKeyImpl<K> extends StatsKeyImpl<K, Boolean> implements BooleanStatsKey<K> {

    static final BooleanStatsKey<String> IDENTITY = BooleanStatsKey.<String>builder()
            .keyBuilder(builder -> builder.key(Key.identity()))
            .build();

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
