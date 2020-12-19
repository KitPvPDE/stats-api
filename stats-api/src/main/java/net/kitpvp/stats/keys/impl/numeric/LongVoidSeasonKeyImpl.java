package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.keys.impl.VoidSeasonKeyImpl;
import net.kitpvp.stats.keys.numeric.LongSSeasonKey;
import net.kitpvp.stats.keys.numeric.LongSStatsKey;

import java.util.function.Function;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

public class LongVoidSeasonKeyImpl extends VoidSeasonKeyImpl<Long, LongSStatsKey> implements LongSSeasonKey {

    private final LongBinaryOperator sumFunction;
    private final LongUnaryOperator inverse;
    private final long neutral, def, offset;

    public LongVoidSeasonKeyImpl(VoidKeyFunction keyFunction, LongBinaryOperator sumFunction, LongUnaryOperator inverse, long neutral, long def, long offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected LongSStatsKey createKey(int season) {
        return new LongVoidStatsKeyImpl(this.createKeyFunction(season), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
