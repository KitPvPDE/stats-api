package net.kitpvp.stats.builder.numeric;

import net.kitpvp.stats.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.builders.KeyBuilder;
import net.kitpvp.stats.keys.impl.numeric.IntSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.IntStageKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.IntStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.IntSeasonKey;
import net.kitpvp.stats.keys.numeric.IntStageKey;
import net.kitpvp.stats.keys.numeric.IntStatsKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;

public class IntKeyBuilder<K> implements StatsKeyBuilder<K, Integer> {

    protected final KeyBuilder<K> keyBuilder;
    protected IntBinaryOperator function = Integer::sum;
    protected int neutral = 0, def = 0, offset = 0;

    public IntKeyBuilder() {
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
        return new IntStatsKeyImpl<>(this.keyBuilder.build(), this.function, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IntSeasonKey<K> season() {
        return new IntSeasonKeyImpl<>(this.keyBuilder.build(), this.function, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IntStageKey<K> stage() {
        return new IntStageKeyImpl<>(this.keyBuilder.build(), this.function, this.neutral, this.def, this.offset);
    }
}
