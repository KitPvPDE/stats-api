package net.kitpvp.stats.keys;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

class DoubleSeasonKeyImpl<K> extends SeasonKeyImpl<K, Double, DoubleStatsKey<K>> implements DoubleSeasonKey<K> {

    private final DoubleBinaryOperator sumFunction;
    private final DoubleUnaryOperator inverse;
    private final double neutral, def, offset;

    DoubleSeasonKeyImpl(KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, DoubleBinaryOperator sumFunction, DoubleUnaryOperator inverse, double neutral, double def, double offset) {
        super(keyFunction, seasonKeyMapping);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public DoubleVoidSeasonKey bind(K k) {
        return new DoubleVoidSeasonKeyImpl(this.keyFunction.bind(k), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected DoubleStatsKey<K> createKey(KeyFunction<K> function) {
        return new DoubleStatsKeyImpl<>(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
