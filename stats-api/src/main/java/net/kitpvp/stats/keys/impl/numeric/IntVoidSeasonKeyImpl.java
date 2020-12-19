package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.keys.impl.VoidSeasonKeyImpl;
import net.kitpvp.stats.keys.numeric.IntSSeasonKey;
import net.kitpvp.stats.keys.numeric.IntSStatsKey;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class IntVoidSeasonKeyImpl extends VoidSeasonKeyImpl<Integer, IntSStatsKey> implements IntSSeasonKey {

    private final IntBinaryOperator sumFunction;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    public IntVoidSeasonKeyImpl(VoidKeyFunction keyFunction, IntBinaryOperator sumFunction, IntUnaryOperator inverse, int neutral, int def, int offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected IntSStatsKey createKey(int season) {
        return new IntVoidStatsKeyImpl(this.createKeyFunction(season), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
