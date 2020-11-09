package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.VoidStageKeyImpl;
import net.kitpvp.stats.keys.numeric.LongSStageKey;
import net.kitpvp.stats.keys.numeric.LongSStatsKey;

import java.util.function.Function;
import java.util.function.LongBinaryOperator;

public class LongVoidStageKeyImpl extends VoidStageKeyImpl<Long, LongSStatsKey> implements LongSStageKey {

    private final LongBinaryOperator sumFunction;
    private final long neutral, def, offset;

    public LongVoidStageKeyImpl(Function<Void, String> keyFunction, LongBinaryOperator sumFunction, long neutral, long def, long offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected LongSStatsKey createKey(int season, int stage) {
        return new LongVoidStatsKeyImpl(this.createKeyFunction(season, stage), this.sumFunction, this.neutral, this.def, this.offset);
    }

    @Override
    protected LongSStatsKey createKey(int season) {
        return new LongVoidStatsKeyImpl(this.createKeyFunction(season), this.sumFunction, this.neutral, this.def, this.offset);
    }
}
