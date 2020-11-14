package net.kitpvp.stats.builder.numeric;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.KeyBuilder;
import net.kitpvp.stats.builder.keys.VoidKeyBuilder;
import net.kitpvp.stats.keys.impl.numeric.*;
import net.kitpvp.stats.keys.numeric.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public class DoubleVoidKeyBuilder extends DoubleKeyBuilder<Void> implements StatsKeyBuilder<Void, Double> {

    protected final VoidKeyBuilder keyBuilder;
    protected DoubleBinaryOperator function = Double::sum;
    protected double neutral = 0, def = 0, offset = 0;

    public DoubleVoidKeyBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
    }

    public DoubleVoidKeyBuilder keyBuilder(Consumer<KeyBuilder<Void>> consumer) {
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
    public @NotNull DoubleSStatsKey build() {
        return new DoubleVoidStatsKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull DoubleSSeasonKey season() {
        return new DoubleVoidSeasonKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull DoubleSStageKey stage() {
        return new DoubleVoidStageKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }
}
