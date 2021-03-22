package net.kitpvp.stats.keys;

class BooleanVoidSeasonKeyImpl extends VoidSeasonKeyImpl<Boolean, BooleanVoidStatsKey> implements BooleanVoidSeasonKey {

    private final boolean def;

    BooleanVoidSeasonKeyImpl(VoidKeyFunction keyFunction, boolean def) {
        super(keyFunction);
        this.def = def;
    }

    @Override
    protected BooleanVoidStatsKey createKey(VoidKeyFunction function) {
        return new BooleanVoidStatsKeyImpl(function, this.def);
    }
}
