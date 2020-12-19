package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.keys.impl.VoidStageKeyImpl;
import net.kitpvp.stats.keys.numeric.DoubleSStageKey;
import net.kitpvp.stats.keys.numeric.DoubleSStatsKey;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class DoubleVoidStageKeyImpl extends VoidStageKeyImpl<Double, DoubleSStatsKey> implements DoubleSStageKey {

    private final DoubleBinaryOperator sumFunction;
    private final DoubleUnaryOperator inverse;
    private final double neutral, def, offset;

    public DoubleVoidStageKeyImpl(VoidKeyFunction keyFunction, DoubleBinaryOperator sumFunction, DoubleUnaryOperator inverse, double neutral, double def, double offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected DoubleSStatsKey createKey(int season, int stage) {
        return new DoubleVoidStatsKeyImpl(this.createKeyFunction(season, stage), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    protected DoubleSStatsKey createKey(int season) {
        return new DoubleVoidStatsKeyImpl(this.createKeyFunction(season), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
