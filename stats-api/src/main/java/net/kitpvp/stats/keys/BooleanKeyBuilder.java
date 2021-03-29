package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class BooleanKeyBuilder<K> implements StatsKeyBuilder<K, Boolean> {

    protected final KeyBuilder<K> keyBuilder;
    protected boolean def = false;

    BooleanKeyBuilder() {
        this.keyBuilder = new KeyBuilder<>();
    }

    public BooleanKeyBuilder<K> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public BooleanKeyBuilder<K> def(boolean b) {
        this.def = b;
        return this;
    }

    @Override
    public @NotNull BooleanStatsKey<K> build() {
        return new BooleanStatsKeyImpl<>(this.keyBuilder.build(), this.def);
    }

    @Override
    public @NotNull BooleanSeasonKey<K> season() {
        return new BooleanSeasonKeyImpl<>(this.keyBuilder.build(), this.def);
    }

    @Override
    public @NotNull BooleanStageKey<K> stage(UnaryOperator<KeyFunction<K>> remapFunction) {
        return new BooleanStageKeyImpl<>(this.keyBuilder.build(), remapFunction, this.def);
    }
}
