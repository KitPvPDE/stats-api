package net.kitpvp.stats.builder.numeric;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.VoidKeyBuilder;
import net.kitpvp.stats.keys.*;
import net.kitpvp.stats.keys.impl.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class NumericVoidKeyBuilder<V> implements StatsKeyBuilder<Void, V> {

    protected final VoidKeyBuilder keyBuilder = new VoidKeyBuilder();
    private BinaryOperator<V> addition;
    private UnaryOperator<V> reverse;
    private V neutral, def, offset;

    public NumericVoidKeyBuilder<V> def(V def) {
        this.def = def;
        return this;
    }

    public NumericVoidKeyBuilder<V> offset(V offset) {
        this.offset = offset;
        return this;
    }

    public NumericVoidKeyBuilder<V> neutral(V neutral) {
        this.neutral = neutral;
        return this;
    }

    public NumericVoidKeyBuilder<V> addition(BinaryOperator<V> addition) {
        this.addition = addition;
        return this;
    }

    public NumericVoidKeyBuilder<V> inverse(UnaryOperator<V> inverse) {
        this.reverse = inverse;
        return this;
    }

    @Override
    public @NotNull IncSStatsKey<V> build() {
        return new NumericVoidStatsKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IncSSeasonKey<V> season() {
        return new NumericVoidSeasonKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IncSStageKey<V> stage() {
        return new NumericVoidStageKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }
}
