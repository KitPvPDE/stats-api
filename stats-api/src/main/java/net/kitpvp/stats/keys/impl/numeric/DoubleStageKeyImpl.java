package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.StageKeyImpl;
import net.kitpvp.stats.keys.numeric.DoubleStageKey;
import net.kitpvp.stats.keys.numeric.DoubleStatsKey;

import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

public class DoubleStageKeyImpl<K> extends StageKeyImpl<K, Double, DoubleStatsKey<K>> implements DoubleStageKey<K> {

    private final DoubleBinaryOperator sumFunction;
    private final double neutral, def, offset;

    public DoubleStageKeyImpl(Function<K, String> keyFunction, DoubleBinaryOperator sumFunction, double neutral, double def, double offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected DoubleStatsKey<K> createKey(int season, int stage) {
        return new DoubleStatsKeyImpl<>(this.createKeyFunction(season, stage), this.sumFunction, this.neutral, this.def, this.offset);
    }

    @Override
    protected DoubleStatsKey<K> createKey(int season) {
        return new DoubleStatsKeyImpl<>(this.createKeyFunction(season), this.sumFunction, this.neutral, this.def, this.offset);
    }
}
