package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.impl.StageKeyImpl;
import net.kitpvp.stats.keys.numeric.DoubleStageKey;
import net.kitpvp.stats.keys.numeric.DoubleStatsKey;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class DoubleStageKeyImpl<K> extends StageKeyImpl<K, Double, DoubleStatsKey<K>> implements DoubleStageKey<K> {

    private final DoubleBinaryOperator sumFunction;
    private final DoubleUnaryOperator inverse;
    private final double neutral, def, offset;

    public DoubleStageKeyImpl(KeyFunction<K> keyFunction, DoubleBinaryOperator sumFunction, DoubleUnaryOperator inverse, double neutral, double def, double offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.inverse = inverse;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected DoubleStatsKey<K> createKey(int season, int stage) {
        return new DoubleStatsKeyImpl<>(this.createKeyFunction(season, stage), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected DoubleStatsKey<K> createKey(int season) {
        return new DoubleStatsKeyImpl<>(this.createKeyFunction(season), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
