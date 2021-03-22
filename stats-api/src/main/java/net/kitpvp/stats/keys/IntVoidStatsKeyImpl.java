package net.kitpvp.stats.keys;

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

class IntVoidStatsKeyImpl extends NumericVoidStatsKeyImpl<Integer> implements IntVoidStatsKey {

    private final IntBinaryOperator function;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    IntVoidStatsKeyImpl(VoidKeyFunction keyFunction, IntBinaryOperator function, IntUnaryOperator inverse, int neutral, int def, int offset) {
        super(keyFunction, function::applyAsInt, inverse::applyAsInt, neutral, def, offset);
        this.function = function;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public int applyInt(int i) {
        return this.function.applyAsInt(i, this.offset);
    }

    @Override
    public int defInt() {
        return this.def;
    }

    @Override
    public int neutralInt() {
        return this.neutral;
    }

    @Override
    public int offsetInt() {
        return this.offset;
    }

    @Override
    public IntBinaryOperator additionInt() {
        return this.function;
    }

    @Override
    public IntUnaryOperator inverseInt() {
        return this.inverse;
    }

    @Override
    public IntVoidStatsKey bind(Void unused) {
        return this;
    }
}
