package net.kitpvp.stats.builder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.builder.builders.KeyBuilder;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.keys.impl.StatsKeyImpl;
import net.kitpvp.stats.keys.impl.functions.FinalSupplier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Builder<K, V> implements ComponentBuilder<AppendableKey<K, V>> {

    protected final KeyBuilder<K> keyBuilder;
    protected Supplier<V> defaultSupplier;

    public Builder() {
        this.keyBuilder = new KeyBuilder<>();
        this.defaultSupplier = null;
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
        return new StatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }
}
