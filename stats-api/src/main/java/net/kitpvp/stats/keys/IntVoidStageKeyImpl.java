package net.kitpvp.stats.keys;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

class IntVoidStageKeyImpl extends VoidStageKeyImpl<Integer, IntVoidStatsKey> implements IntVoidStageKey {

    private final IntBinaryOperator sumFunction;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    IntVoidStageKeyImpl(VoidKeyFunction keyFunction, UnaryOperator<VoidKeyFunction> remapFunction, IntBinaryOperator sumFunction, IntUnaryOperator inverse, int neutral, int def, int offset) {
        super(keyFunction, remapFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected IntVoidStatsKey createKey(VoidKeyFunction function) {
        return new IntVoidStatsKeyImpl(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
