package net.kitpvp.stats.keys;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
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
    public @NotNull NumericVoidStatsKey<V> build() {
        return new NumericVoidStatsKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull NumericVoidSeasonKey<V> season() {
        return new NumericVoidSeasonKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull NumericVoidStageKey<V> stage() {
        return new NumericVoidStageKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }
}
