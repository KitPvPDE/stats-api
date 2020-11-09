package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.VoidSeasonKeyImpl;
import net.kitpvp.stats.keys.numeric.IntSSeasonKey;
import net.kitpvp.stats.keys.numeric.IntSStatsKey;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class IntVoidSeasonKeyImpl extends VoidSeasonKeyImpl<Integer, IntSStatsKey> implements IntSSeasonKey {

    private final IntBinaryOperator sumFunction;
    private final int neutral, def, offset;

    public IntVoidSeasonKeyImpl(Function<Void, String> keyFunction, IntBinaryOperator sumFunction, int neutral, int def, int offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected IntSStatsKey createKey(int season) {
        return new IntVoidStatsKeyImpl(this.createKeyFunction(season), this.sumFunction, this.neutral, this.def, this.offset);
    }
}
