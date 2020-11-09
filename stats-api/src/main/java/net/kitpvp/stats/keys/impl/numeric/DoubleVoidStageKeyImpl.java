package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.VoidStageKeyImpl;
import net.kitpvp.stats.keys.numeric.DoubleSStageKey;
import net.kitpvp.stats.keys.numeric.DoubleSStatsKey;

import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

public class DoubleVoidStageKeyImpl extends VoidStageKeyImpl<Double, DoubleSStatsKey> implements DoubleSStageKey {

    private final DoubleBinaryOperator sumFunction;
    private final double neutral, def, offset;

    public DoubleVoidStageKeyImpl(Function<Void, String> keyFunction, DoubleBinaryOperator sumFunction, double neutral, double def, double offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected DoubleSStatsKey createKey(int season, int stage) {
        return new DoubleVoidStatsKeyImpl(this.createKeyFunction(season, stage), this.sumFunction, this.neutral, this.def, this.offset);
    }

    @Override
    protected DoubleSStatsKey createKey(int season) {
        return new DoubleVoidStatsKeyImpl(this.createKeyFunction(season), this.sumFunction, this.neutral, this.def, this.offset);
    }
}
