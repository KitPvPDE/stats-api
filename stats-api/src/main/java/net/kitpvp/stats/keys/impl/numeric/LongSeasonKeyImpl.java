package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.SeasonKeyImpl;
import net.kitpvp.stats.keys.numeric.LongSeasonKey;
import net.kitpvp.stats.keys.numeric.LongStatsKey;

import java.util.function.Function;
import java.util.function.LongBinaryOperator;

public class LongSeasonKeyImpl<K> extends SeasonKeyImpl<K, Long, LongStatsKey<K>> implements LongSeasonKey<K> {

    private final LongBinaryOperator sumFunction;
    private final long neutral, def, offset;

    public LongSeasonKeyImpl(Function<K, String> keyFunction, LongBinaryOperator sumFunction, long neutral, long def, long offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected LongStatsKey<K> createKey(int season) {
        return new LongStatsKeyImpl<>(this.createKeyFunction(season), this.sumFunction, this.neutral, this.def, this.offset);
    }
}
