package net.kitpvp.stats.keys;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.api.functions.season.SeasonKeyFunction;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Builder<K, V> implements StatsKeyBuilder<K, V> {

    private final KeyBuilder<K> keyBuilder;
    private BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping = SeasonKeyFunction::new;
    private Supplier<V> defaultSupplier;

    Builder() {
        this.keyBuilder = new KeyBuilder<>();
        this.defaultSupplier = () -> null;
    }

    public Builder<K, V> defaultNull() {
        this.defaultSupplier = () -> null;
        return this;
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

        return new NormalSeasonKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), seasonKeyMapping);
    }

    @Override
    public @NotNull StageKey<K, V> stage(UnaryOperator<KeyFunction<K>> remapFunction) {
        this.checkNotNull();

        return new NormalStageKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), seasonKeyMapping, remapFunction);
    }

    protected void checkNotNull() {
        Objects.requireNonNull(this.defaultSupplier, "defaultValue");
    }
}
