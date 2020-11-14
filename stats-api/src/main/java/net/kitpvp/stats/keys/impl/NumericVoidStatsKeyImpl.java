package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.keys.IncSStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class NumericVoidStatsKeyImpl<V> extends NumericStatsKeyImpl<Void, V> implements IncSStatsKey<V> {

    public NumericVoidStatsKeyImpl(Function<Void, String> toKey, BinaryOperator<V> addition, UnaryOperator<V> inverse, V neutral, V def, V offset) {
        super(toKey, addition, inverse, neutral, def, offset);
    }
}
