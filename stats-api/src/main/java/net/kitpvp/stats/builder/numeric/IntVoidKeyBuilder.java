package net.kitpvp.stats.builder.numeric;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.KeyBuilder;
import net.kitpvp.stats.builder.keys.VoidKeyBuilder;
import net.kitpvp.stats.keys.impl.numeric.IntVoidSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.IntVoidStageKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.IntVoidStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.IntSSeasonKey;
import net.kitpvp.stats.keys.numeric.IntSStageKey;
import net.kitpvp.stats.keys.numeric.IntSStatsKey;
import net.kitpvp.stats.utils.Functions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class IntVoidKeyBuilder extends IntKeyBuilder<Void> implements StatsKeyBuilder<Void, Integer> {

    protected final VoidKeyBuilder keyBuilder;
    protected IntBinaryOperator function = Integer::sum;
    protected IntUnaryOperator inverse = Functions::inverse;
    protected int neutral, def, offset;

    public IntVoidKeyBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
    }

    public IntVoidKeyBuilder keyBuilder(Consumer<KeyBuilder<Void>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public IntVoidKeyBuilder function(IntBinaryOperator operator) {
        this.function = operator;
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
    public @NotNull IntSStatsKey build() {
        return new IntVoidStatsKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IntSSeasonKey season() {
        return new IntVoidSeasonKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull IntSStageKey stage() {
        return new IntVoidStageKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }
}
