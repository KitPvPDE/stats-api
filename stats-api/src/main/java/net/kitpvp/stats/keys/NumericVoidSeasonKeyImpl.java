package net.kitpvp.stats.keys;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

class NumericVoidSeasonKeyImpl<V> extends VoidSeasonKeyImpl<V, NumericVoidStatsKey<V>> implements NumericVoidSeasonKey<V> {

    private final BinaryOperator<V> sumFunction;
    private final UnaryOperator<V> inverse;
    private final V neutral, def, offset;

    NumericVoidSeasonKeyImpl(VoidKeyFunction keyFunction, BinaryOperator<V> sumFunction, UnaryOperator<V> inverse, V neutral, V def, V offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected NumericVoidStatsKey<V> createKey(VoidKeyFunction function) {
        return new NumericVoidStatsKeyImpl<>(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
