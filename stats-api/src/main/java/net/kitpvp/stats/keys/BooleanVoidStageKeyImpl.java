package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

class BooleanVoidStageKeyImpl extends VoidStageKeyImpl<Boolean, BooleanVoidStatsKey> implements BooleanVoidStageKey {

    private final boolean def;

    BooleanVoidStageKeyImpl(VoidKeyFunction keyFunction, UnaryOperator<VoidKeyFunction> remapFunction, boolean def) {
        super(keyFunction, remapFunction);
        this.def = def;
    }

    @Override
    protected BooleanVoidStatsKey createKey(VoidKeyFunction function) {
        return new BooleanVoidStatsKeyImpl(function, this.def);
    }
}
