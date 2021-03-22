package net.kitpvp.stats.keys;

class BooleanVoidStageKeyImpl extends VoidStageKeyImpl<Boolean, BooleanVoidStatsKey> implements BooleanVoidStageKey {

    private final boolean def;

    BooleanVoidStageKeyImpl(VoidKeyFunction keyFunction, boolean def) {
        super(keyFunction);
        this.def = def;
    }

    @Override
    protected BooleanVoidStatsKey createKey(VoidKeyFunction function) {
        return new BooleanVoidStatsKeyImpl(function, this.def);
    }
}
