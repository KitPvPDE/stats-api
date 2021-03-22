package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.utils.Functions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

public class LongKeyBuilder<K> implements StatsKeyBuilder<K, Long> {

    protected final KeyBuilder<K> keyBuilder;
    protected LongBinaryOperator function = Long::sum;
    protected LongUnaryOperator inverse = Functions::inverse;
    protected long neutral = 0, def = 0, offset = 0;

    LongKeyBuilder() {
        this.keyBuilder = new KeyBuilder<>();
    }

    public LongKeyBuilder<K> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public LongKeyBuilder<K> function(LongBinaryOperator operator) {
        this.function = operator;
        return this;
    }

    public LongKeyBuilder<K> neutral(long i) {
        this.neutral = i;
        return this;
    }

    public LongKeyBuilder<K> def(long i) {
        this.def = i;
        return this;
    }

    public LongKeyBuilder<K> offset(long i) {
        this.offset = i;
        return this;
    }

    @Override
    public @NotNull LongStatsKey<K> build() {
        return new LongStatsKeyImpl<>(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull LongSeasonKey<K> season() {
        return new LongSeasonKeyImpl<>(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull LongStageKey<K> stage() {
        return new LongStageKeyImpl<>(this.keyBuilder.build(), this.function, this.inverse, this.neutral, this.def, this.offset);
    }
}
