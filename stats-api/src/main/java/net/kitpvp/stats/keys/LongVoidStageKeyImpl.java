package net.kitpvp.stats.keys;

import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

class LongVoidStageKeyImpl extends VoidStageKeyImpl<Long, LongVoidStatsKey> implements LongVoidStageKey {

    private final LongBinaryOperator sumFunction;
    private final LongUnaryOperator inverse;
    private final long neutral, def, offset;

    LongVoidStageKeyImpl(VoidKeyFunction keyFunction, LongBinaryOperator sumFunction, LongUnaryOperator inverse, long neutral, long def, long offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected LongVoidStatsKey createKey(VoidKeyFunction function) {
        return new LongVoidStatsKeyImpl(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
