package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.keys.impl.VoidStageKeyImpl;
import net.kitpvp.stats.keys.numeric.IntSStageKey;
import net.kitpvp.stats.keys.numeric.IntSStatsKey;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class IntVoidStageKeyImpl extends VoidStageKeyImpl<Integer, IntSStatsKey> implements IntSStageKey {

    private final IntBinaryOperator sumFunction;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    public IntVoidStageKeyImpl(VoidKeyFunction keyFunction, IntBinaryOperator sumFunction, IntUnaryOperator inverse, int neutral, int def, int offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected IntSStatsKey createKey(int season, int stage) {
        return new IntVoidStatsKeyImpl(this.createKeyFunction(season, stage), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected IntSStatsKey createKey(int season) {
        return new IntVoidStatsKeyImpl(this.createKeyFunction(season), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
