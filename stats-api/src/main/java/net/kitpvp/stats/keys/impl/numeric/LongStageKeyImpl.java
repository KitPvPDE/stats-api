package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.StageKeyImpl;
import net.kitpvp.stats.keys.numeric.LongStageKey;
import net.kitpvp.stats.keys.numeric.LongStatsKey;

import java.util.function.Function;
import java.util.function.LongBinaryOperator;

public class LongStageKeyImpl<K> extends StageKeyImpl<K, Long, LongStatsKey<K>> implements LongStageKey<K> {

    private final LongBinaryOperator sumFunction;
    private final long neutral, def, offset;

    public LongStageKeyImpl(Function<K, String> keyFunction, LongBinaryOperator sumFunction, long neutral, long def, long offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected LongStatsKey<K> createKey(int season, int stage) {
        return new LongStatsKeyImpl<>(this.createKeyFunction(season, stage), this.sumFunction, this.neutral, this.def, this.offset);
    }

    @Override
    protected LongStatsKey<K> createKey(int season) {
        return new LongStatsKeyImpl<>(this.createKeyFunction(season), this.sumFunction, this.neutral, this.def, this.offset);
    }
}
