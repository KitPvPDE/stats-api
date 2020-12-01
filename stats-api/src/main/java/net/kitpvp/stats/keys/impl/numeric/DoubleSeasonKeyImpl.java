package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.impl.SeasonKeyImpl;
import net.kitpvp.stats.keys.numeric.DoubleSeasonKey;
import net.kitpvp.stats.keys.numeric.DoubleStatsKey;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class DoubleSeasonKeyImpl<K> extends SeasonKeyImpl<K, Double, DoubleStatsKey<K>> implements DoubleSeasonKey<K> {

    private final DoubleBinaryOperator sumFunction;
    private final DoubleUnaryOperator inverse;
    private final double neutral, def, offset;

    public DoubleSeasonKeyImpl(KeyFunction<K> keyFunction, DoubleBinaryOperator sumFunction, DoubleUnaryOperator inverse, double neutral, double def, double offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected DoubleStatsKey<K> createKey(int season) {
        return new DoubleStatsKeyImpl<>(this.createKeyFunction(season), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
