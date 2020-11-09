package net.kitpvp.stats.builder.numeric;

import net.kitpvp.stats.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.builders.KeyBuilder;
import net.kitpvp.stats.builder.builders.VoidKeyBuilder;
import net.kitpvp.stats.keys.impl.numeric.LongVoidSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.LongVoidStageKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.LongVoidStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.LongSSeasonKey;
import net.kitpvp.stats.keys.numeric.LongSStageKey;
import net.kitpvp.stats.keys.numeric.LongSStatsKey;
import net.kitpvp.stats.keys.numeric.LongStageKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;

public class LongVoidKeyBuilder extends LongKeyBuilder<Void> implements StatsKeyBuilder<Void, Long> {

    protected final VoidKeyBuilder keyBuilder;
    protected LongBinaryOperator function = Long::sum;
    protected long neutral, def, offset;

    public LongVoidKeyBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
    }

    public LongVoidKeyBuilder keyBuilder(Consumer<KeyBuilder<Void>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public LongVoidKeyBuilder function(LongBinaryOperator operator) {
        this.function = operator;
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
    public @NotNull LongSStatsKey build() {
        return new LongVoidStatsKeyImpl(this.keyBuilder.build(), this.function, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull LongSSeasonKey season() {
        return new LongVoidSeasonKeyImpl(this.keyBuilder.build(), this.function, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull LongSStageKey stage() {
        return new LongVoidStageKeyImpl(this.keyBuilder.build(), this.function, this.neutral, this.def, this.offset);
    }
}
