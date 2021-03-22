package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.utils.Functions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class DoubleVoidKeyBuilder implements StatsKeyBuilder<Void, Double> {

    protected final VoidKeyBuilder keyBuilder;
    protected DoubleBinaryOperator function = Double::sum;
    protected DoubleUnaryOperator inverse = Functions::inverse;
    protected double neutral = 0, def = 0, offset = 0;

    DoubleVoidKeyBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
    }

    public DoubleVoidKeyBuilder keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public DoubleVoidKeyBuilder addition(DoubleBinaryOperator addition) {
        this.function = addition;
        return this;
    }

    public DoubleVoidKeyBuilder inverse(DoubleUnaryOperator inverse) {
        this.inverse = inverse;
        return this;
    }

    public DoubleVoidKeyBuilder neutral(double i) {
        this.neutral = i;
        return this;
    }

    public DoubleVoidKeyBuilder def(double i) {
        this.def = i;
        return this;
    }

    public DoubleVoidKeyBuilder offset(double i) {
        this.offset = i;
        return this;
    }

    @Override
    public @NotNull DoubleVoidStatsKey build() {
        return new DoubleVoidStatsKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull DoubleVoidSeasonKey season() {
        return new DoubleVoidSeasonKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull DoubleVoidStageKey stage() {
        return new DoubleVoidStageKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }
}
