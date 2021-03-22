package net.kitpvp.stats.keys;

class BooleanVoidStatsKeyImpl extends VoidStatsKeyImpl<Boolean> implements BooleanVoidStatsKey {

    private final boolean def;

    BooleanVoidStatsKeyImpl(VoidKeyFunction keyFunction, boolean def) {
        super(null, keyFunction);
        this.def = def;
    }

    @Override
    public boolean applyBoolean(boolean b) {
        return b;
    }

    @Override
    public boolean defBoolean() {
        return this.def;
    }

    @Override
    public Boolean def() {
        return this.def;
    }
}
