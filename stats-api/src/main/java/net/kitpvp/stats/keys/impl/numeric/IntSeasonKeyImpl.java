package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.SeasonKeyImpl;
import net.kitpvp.stats.keys.numeric.IntSeasonKey;
import net.kitpvp.stats.keys.numeric.IntStatsKey;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class IntSeasonKeyImpl<K> extends SeasonKeyImpl<K, Integer, IntStatsKey<K>> implements IntSeasonKey<K> {

    private final IntBinaryOperator sumFunction;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    public IntSeasonKeyImpl(Function<K, String> keyFunction, IntBinaryOperator sumFunction, IntUnaryOperator inverse, int neutral, int def, int offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public IntStatsKey<K> season(int season) {
        return super.season(season);
    }

    @Override
    protected IntStatsKey<K> createKey(int season) {
        return new IntStatsKeyImpl<>(this.createKeyFunction(season), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
