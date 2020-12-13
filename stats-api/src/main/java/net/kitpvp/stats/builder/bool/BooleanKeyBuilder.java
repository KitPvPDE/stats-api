package net.kitpvp.stats.builder.bool;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.KeyBuilder;
import net.kitpvp.stats.keys.bool.BooleanSeasonKey;
import net.kitpvp.stats.keys.bool.BooleanStageKey;
import net.kitpvp.stats.keys.bool.BooleanStatsKey;
import net.kitpvp.stats.keys.impl.bool.BooleanSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.bool.BooleanStageKeyImpl;
import net.kitpvp.stats.keys.impl.bool.BooleanStatsKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.IntSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.IntStageKeyImpl;
import net.kitpvp.stats.keys.impl.numeric.IntStatsKeyImpl;
import net.kitpvp.stats.keys.numeric.IntSeasonKey;
import net.kitpvp.stats.keys.numeric.IntStageKey;
import net.kitpvp.stats.keys.numeric.IntStatsKey;
import net.kitpvp.stats.utils.Functions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class BooleanKeyBuilder<K> implements StatsKeyBuilder<K, Boolean> {

    protected final KeyBuilder<K> keyBuilder;
    protected boolean def = false;

    public BooleanKeyBuilder() {
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
    public @NotNull BooleanStageKey<K> stage() {
        return new BooleanStageKeyImpl<>(this.keyBuilder.build(), this.def);
    }
}
