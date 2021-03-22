package net.kitpvp.stats.keys;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

class IntVoidSeasonKeyImpl extends VoidSeasonKeyImpl<Integer, IntVoidStatsKey> implements IntVoidSeasonKey {

    private final IntBinaryOperator sumFunction;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    IntVoidSeasonKeyImpl(VoidKeyFunction keyFunction, IntBinaryOperator sumFunction, IntUnaryOperator inverse, int neutral, int def, int offset) {
        super(keyFunction);
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
