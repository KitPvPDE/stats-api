package net.kitpvp.stats.builder.numeric;

import net.kitpvp.stats.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.builders.KeyBuilder;
import net.kitpvp.stats.keys.impl.numeric.DoubleSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.DoubleStageKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.DoubleStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.DoubleSeasonKey;
import net.kitpvp.stats.keys.numeric.DoubleStageKey;
import net.kitpvp.stats.keys.numeric.DoubleStatsKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;

public class DoubleKeyBuilder<K> implements StatsKeyBuilder<K, Double> {

    protected final KeyBuilder<K> keyBuilder;
    protected DoubleBinaryOperator function = Double::sum;
    protected double neutral = 0, def = 0, offset = 0;

    public DoubleKeyBuilder() {
        this.keyBuilder = new KeyBuilder<>();
    }

    public DoubleKeyBuilder<K> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public DoubleKeyBuilder<K> function(DoubleBinaryOperator operator) {
        this.function = operator;
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
        return new DoubleStatsKeyImpl<>(this.keyBuilder.build(), this.function, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull DoubleSeasonKey<K> season() {
        return new DoubleSeasonKeyImpl<>(this.keyBuilder.build(), this.function, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull DoubleStageKey<K> stage() {
        return new DoubleStageKeyImpl<>(this.keyBuilder.build(), this.function, this.neutral, this.def, this.offset);
    }
}
