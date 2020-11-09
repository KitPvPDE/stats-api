package net.kitpvp.stats.keys.impl.numeric;

import net.kitpvp.stats.keys.impl.VoidSeasonKeyImpl;
import net.kitpvp.stats.keys.numeric.DoubleSSeasonKey;
import net.kitpvp.stats.keys.numeric.DoubleSStatsKey;

import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;

public class DoubleVoidSeasonKeyImpl extends VoidSeasonKeyImpl<Double, DoubleSStatsKey> implements DoubleSSeasonKey {

    private final DoubleBinaryOperator sumFunction;
    private final double neutral, def, offset;

    public DoubleVoidSeasonKeyImpl(Function<Void, String> keyFunction, DoubleBinaryOperator sumFunction, double neutral, double def, double offset) {
        super(keyFunction);
        this.sumFunction = sumFunction;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    protected DoubleSStatsKey createKey(int season) {
        return new DoubleVoidStatsKeyImpl(this.createKeyFunction(season), this.sumFunction, this.neutral, this.def, this.offset);
    }
}
