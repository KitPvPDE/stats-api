package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.utils.Functions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class IntKeyBuilder<K> implements StatsKeyBuilder<K, Integer> {

    protected final KeyBuilder<K> keyBuilder;
    protected IntBinaryOperator function = Integer::sum;
    protected IntUnaryOperator inverse = Functions::inverse;
    protected int neutral = 0, def = 0, offset = 0;

    IntKeyBuilder() {
        this.keyBuilder = new KeyBuilder<>();
    }

    public IntKeyBuilder<K> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public IntKeyBuilder<K> function(IntBinaryOperator operator) {
        this.function = operator;
        return this;
    }

    public IntKeyBuilder<K> inverse(IntUnaryOperator inverse) {
        this.inverse = inverse;
        return this;
    }

    public IntKeyBuilder<K> neutral(int i) {
        this.neutral = i;
        return this;
    }

    public IntKeyBuilder<K> def(int i) {
        this.def = i;
        return this;
    }

    public IntKeyBuilder<K> offset(int i) {
        this.offset = i;
        return this;
    }

    @Override
    public @NotNull IntStatsKey<K> build() {
        return new IntStatsKeyImpl<>(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IntSeasonKey<K> season() {
        return new IntSeasonKeyImpl<>(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IntStageKey<K> stage(UnaryOperator<KeyFunction<K>> remapFunction) {
        return new IntStageKeyImpl<>(this.keyBuilder.build(), remapFunction, this.function, this.inverse, this.neutral, this.def, this.offset);
    }
}
