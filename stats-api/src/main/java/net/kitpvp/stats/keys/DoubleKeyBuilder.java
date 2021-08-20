package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.api.functions.season.SeasonKeyFunction;
import net.kitpvp.stats.utils.Functions;
import org.jetbrains.annotations.NotNull;

import java.util.function.*;

public class DoubleKeyBuilder<K> implements StatsKeyBuilder<K, Double> {

    protected final KeyBuilder<K> keyBuilder;
    protected BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping = SeasonKeyFunction::new;
    protected DoubleBinaryOperator addition = Double::sum;
    protected DoubleUnaryOperator inverse = Functions::inverse;
    protected double neutral = 0, def = 0, offset = 0;

    DoubleKeyBuilder() {
        this.keyBuilder = new KeyBuilder<>();
    }

    public DoubleKeyBuilder<K> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public DoubleKeyBuilder<K> seasonKeyMapping(BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping) {
        this.seasonKeyMapping = seasonKeyMapping;
        return this;
    }

    public DoubleKeyBuilder<K> addition(DoubleBinaryOperator addition) {
        this.addition = addition;
        return this;
    }

    public DoubleKeyBuilder<K> inverse(DoubleUnaryOperator function) {
        this.inverse = function;
        return this;
    }

    public DoubleKeyBuilder<K> neutral(double i) {
        this.neutral = i;
        return this;
    }

    public DoubleKeyBuilder<K> def(double i) {
        this.def = i;
        return this;
    }

    public DoubleKeyBuilder<K> offset(double i) {
        this.offset = i;
        return this;
    }

    @Override
    public @NotNull DoubleStatsKey<K> build() {
        return new DoubleStatsKeyImpl<>(this.keyBuilder.build(), this.addition, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull DoubleSeasonKey<K> season() {
        return new DoubleSeasonKeyImpl<>(this.keyBuilder.build(), seasonKeyMapping, this.addition, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull DoubleStageKey<K> stage(UnaryOperator<KeyFunction<K>> remapFunction) {
        return new DoubleStageKeyImpl<>(this.keyBuilder.build(), seasonKeyMapping, remapFunction, this.addition, this.inverse, this.neutral, this.def, this.offset);
    }
}
