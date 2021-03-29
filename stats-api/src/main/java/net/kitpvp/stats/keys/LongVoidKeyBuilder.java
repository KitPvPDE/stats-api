package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.api.builder.VoidStatsKeyBuilder;
import net.kitpvp.stats.utils.Functions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.UnaryOperator;

public class LongVoidKeyBuilder implements VoidStatsKeyBuilder<Long> {

    protected final VoidKeyBuilder keyBuilder;
    protected LongBinaryOperator function = Long::sum;
    protected LongUnaryOperator inverse = Functions::inverse;
    protected long neutral, def, offset;

    LongVoidKeyBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
    }

    public LongVoidKeyBuilder keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public LongVoidKeyBuilder function(LongBinaryOperator operator) {
        this.function = operator;
        return this;
    }

    public LongVoidKeyBuilder inverse(LongUnaryOperator operator) {
        this.inverse = inverse;
        return this;
    }

    public LongVoidKeyBuilder neutral(long i) {
        this.neutral = i;
        return this;
    }

    public LongVoidKeyBuilder def(long i) {
        this.def = i;
        return this;
    }

    public LongVoidKeyBuilder offset(long i) {
        this.offset = i;
        return this;
    }

    @Override
    public @NotNull LongVoidStatsKey build() {
        return new LongVoidStatsKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull LongVoidSeasonKey season() {
        return new LongVoidSeasonKeyImpl(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull LongVoidStageKey stage(UnaryOperator<VoidKeyFunction> remapFunction) {
        return new LongVoidStageKeyImpl(this.keyBuilder.build(), remapFunction, this.function, this.inverse, this.neutral, this.def, this.offset);
    }
}
