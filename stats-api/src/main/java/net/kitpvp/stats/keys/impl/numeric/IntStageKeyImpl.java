package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.StageKeyImpl;
import net.kitpvp.stats.keys.numeric.IntStageKey;
import net.kitpvp.stats.keys.numeric.IntStatsKey;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;

public class IntStageKeyImpl<K> extends StageKeyImpl<K, Integer, IntStatsKey<K>> implements IntStageKey<K> {

    private final IntBinaryOperator sumFunction;
    private final int neutral, def, offset;

    public IntStageKeyImpl(Function<K, String> keyFunction, IntBinaryOperator sumFunction, int neutral, int def, int offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected IntStatsKey<K> createKey(int season, int stage) {
        return new IntStatsKeyImpl<>(this.createKeyFunction(season, stage), this.sumFunction, this.neutral, this.def, this.offset);
    }

    @Override
    protected IntStatsKey<K> createKey(int season) {
        return new IntStatsKeyImpl<>(this.createKeyFunction(season), this.sumFunction, this.neutral, this.def, this.offset);
    }
}
