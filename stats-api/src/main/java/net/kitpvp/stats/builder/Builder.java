package net.kitpvp.stats.builder;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.KeyBuilder;
import net.kitpvp.stats.keys.SeasonKey;
import net.kitpvp.stats.keys.StageKey;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.keys.impl.SeasonKeyImpl;
import net.kitpvp.stats.keys.impl.StageKeyImpl;
import net.kitpvp.stats.keys.impl.StatsKeyImpl;
import net.kitpvp.stats.keys.impl.functions.FinalSupplier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Builder<K, V> implements StatsKeyBuilder<K, V> {

    private final KeyBuilder<K> keyBuilder;
    private Supplier<V> defaultSupplier;

    public Builder() {
        this.keyBuilder = new KeyBuilder<>();
        this.defaultSupplier = () -> null;
    }

    public Builder<K, V> defaultValue(V defaultValue) {
        this.defaultSupplier = new FinalSupplier<>(defaultValue);
        return this;
    }

    public Builder<K, V> defaultValue(Supplier<V> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
        return this;
    }

    public Builder<K, V> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    @Override
    public @NotNull StatsKey<K, V> build() {
        this.checkNotNull();

        return new StatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }

    @Override
    public @NotNull SeasonKey<K, V> season() {
        this.checkNotNull();

        return new SeasonKeyImpl<K, V, StatsKey<K, V>>(StatsKeyImpl::new, this.defaultSupplier, this.keyBuilder.build());
    }

    @Override
    public @NotNull StageKey<K, V> stage() {
        this.checkNotNull();

        return new StageKeyImpl<K, V, StatsKey<K, V>>(StatsKeyImpl::new, this.defaultSupplier, this.keyBuilder.build());
    }

    protected void checkNotNull() {
        Preconditions.checkNotNull(this.defaultSupplier, "defaultValue");
    }
}
