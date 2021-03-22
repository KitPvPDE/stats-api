package net.kitpvp.stats.keys;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

class NumericVoidStatsKeyImpl<V> extends NumericStatsKeyImpl<Void, V> implements NumericVoidStatsKey<V> {

    private final VoidKeyFunction keyFunction;

    NumericVoidStatsKeyImpl(VoidKeyFunction toKey, BinaryOperator<V> addition, UnaryOperator<V> inverse, V neutral, V def, V offset) {
        super(toKey, addition, inverse, neutral, def, offset);
        this.keyFunction = toKey;
    }

    @Override
    public VoidKeyFunction keyFunction() {
        return this.keyFunction;
    }

    @Override
    public NumericVoidStatsKey<V> bind(Void unused) {
        return this;
    }
}
