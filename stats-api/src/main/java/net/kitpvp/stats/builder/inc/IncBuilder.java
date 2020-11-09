package net.kitpvp.stats.builder.inc;

import net.kitpvp.stats.builder.ComponentBuilder;
import net.kitpvp.stats.builder.builders.KeyBuilder;
import net.kitpvp.stats.keys.impl.inc.IncStatsKeyImpl;
import net.kitpvp.stats.keys.IncStatsKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class IncBuilder<K, V> implements ComponentBuilder<IncStatsKey<K, V>> {

    protected final KeyBuilder<K> keyBuilder;
    private BinaryOperator<V> sum;
    private V neutral, offset;

    public IncBuilder(KeyBuilder<K> keyBuilder, BinaryOperator<V> sum, V neutral) {
        this.keyBuilder = keyBuilder;
        this.sum = sum;
        this.neutral = this.offset = neutral;
    }

    public IncBuilder<K, V> offset(V offset) {
        this.offset = offset;
        return this;
    }

    public IncBuilder<K, V> neutral(V neutral) {
        this.neutral = neutral;
        return this;
    }

    @Override
    public @NotNull IncStatsKey<K, V> build() {
        return new IncStatsKeyImpl<>(this.keyBuilder.build(), this.sum, this.neutral, this.offset);
    }
}
