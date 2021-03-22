package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.utils.Functions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class IntVoidKeyBuilder implements StatsKeyBuilder<Void, Integer> {

    protected final VoidKeyBuilder keyBuilder;
    protected IntBinaryOperator function = Integer::sum;
    protected IntUnaryOperator inverse = Functions::inverse;
    protected int neutral, def, offset;

    IntVoidKeyBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
    }

    public IntVoidKeyBuilder keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public IntVoidKeyBuilder function(IntBinaryOperator operator) {
        this.function = operator;
        return this;
    }

    public IntVoidKeyBuilder inverse(IntUnaryOperator operator) {
        this.inverse = operator;
        return this;
    }

    public IntVoidKeyBuilder neutral(int i) {
        this.neutral = i;
        return this;
    }

    public IntVoidKeyBuilder def(int i) {
        this.def = i;
        return this;
    }

    public IntVoidKeyBuilder offset(int i) {
        this.offset = i;
        return this;
    }

    @Override
    public @NotNull IntVoidStatsKey build() {
        return new IntVoidStatsKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IntVoidSeasonKey season() {
        return new IntVoidSeasonKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IntVoidStageKey stage() {
        return new IntVoidStageKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }
}
