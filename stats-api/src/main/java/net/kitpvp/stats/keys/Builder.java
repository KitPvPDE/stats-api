package net.kitpvp.stats.keys;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Builder<K, V> implements StatsKeyBuilder<K, V> {

    private final KeyBuilder<K> keyBuilder;
    private Supplier<V> defaultSupplier;

    Builder() {
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

    public <T, R> MappedBuilder<K, T, R> map(Function<T, R> mapping) {
        return new MappedBuilder<>(this.keyBuilder, mapping);
    }

    @Override
    public @NotNull StatsKey<K, V> build() {
        this.checkNotNull();

        return new StatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }

    @Override
    public @NotNull SeasonKey<K, V> season() {
        this.checkNotNull();

        return new NormalSeasonKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }

    @Override
    public @NotNull StageKey<K, V> stage() {
        this.checkNotNull();

        return new NormalStageKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }

    protected void checkNotNull() {
        Preconditions.checkNotNull(this.defaultSupplier, "defaultValue");
    }
}
