package net.kitpvp.stats.builder.numeric;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.KeyBuilder;
import net.kitpvp.stats.keys.*;
import net.kitpvp.stats.keys.impl.NumericSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.NumericStageKeyImpl;
import net.kitpvp.stats.keys.impl.NumericStatsKeyImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class NumericKeyBuilder<K, V> implements StatsKeyBuilder<K, V> {

    protected final KeyBuilder<K> keyBuilder = new KeyBuilder<>();
    private BinaryOperator<V> addition;
    private UnaryOperator<V> reverse;
    private V neutral, def, offset;

    public NumericKeyBuilder<K, V> def(V def) {
        this.def = def;
        return this;
    }

    public NumericKeyBuilder<K, V> offset(V offset) {
        this.offset = offset;
        return this;
    }

    public NumericKeyBuilder<K, V> neutral(V neutral) {
        this.neutral = neutral;
        return this;
    }

    public NumericKeyBuilder<K, V> addition(BinaryOperator<V> addition) {
        this.addition = addition;
        return this;
    }

    public NumericKeyBuilder<K, V> inverse(UnaryOperator<V> inverse) {
        this.reverse = inverse;
        return this;
    }

    @Override
    public @NotNull IncStatsKey<K, V> build() {
        return new NumericStatsKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IncSeasonKey<K, V> season() {
        return new NumericSeasonKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IncStageKey<K, V> stage() {
        return new NumericStageKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }
}
