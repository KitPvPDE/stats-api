package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.api.functions.season.SeasonKeyFunction;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.*;

public class MappedBuilder<K, V, U> implements StatsKeyBuilder<K, U> {
    private final KeyBuilder<K> keyBuilder;
    private final Function<V, U> mapping;
    private BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping = SeasonKeyFunction::new;
    private Supplier<V> mappingDefaultSupplier;
    private Supplier<U> defaultSupplier;

    MappedBuilder() {
        this.keyBuilder = new KeyBuilder<>();
        this.mapping = null;
        this.defaultSupplier = () -> null;
    }

    MappedBuilder(Function<V, U> mapping) {
        this.keyBuilder = new KeyBuilder<>();
        this.mapping = mapping;
        this.defaultSupplier = () -> null;
    }

    MappedBuilder(KeyBuilder<K> keyBuilder, Function<V, U> mapping) {
        this.keyBuilder = keyBuilder;
        this.mapping = mapping;
        this.defaultSupplier = () -> null;
    }

    public <T, R> MappedBuilder<K, T, R> mapping(Function<T, R> mapping) {
        return new MappedBuilder<>(this.keyBuilder, mapping);
    }

    public MappedBuilder<K, V, U> defaultNull() {
        this.defaultSupplier = () -> null;
        return this;
    }

    public MappedBuilder<K, V, U> defaultValue(U defaultValue) {
        this.defaultSupplier = new FinalSupplier<>(defaultValue);
        return this;
    }

    public MappedBuilder<K, V, U> defaultValue(Supplier<U> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
        return this;
    }

    public MappedBuilder<K, V, U> defaultMappingValue(V mappingDefaultSupplier) {
        this.mappingDefaultSupplier = new FinalSupplier<>(mappingDefaultSupplier);
        return this;
    }

    public MappedBuilder<K, V, U> defaultMappingValue(Supplier<V> mappingDefaultSupplier) {
        this.mappingDefaultSupplier = mappingDefaultSupplier;
        return this;
    }

    public MappedBuilder<K, V, U> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    @Override
    public @NotNull StatsKey<K, U> build() {
        this.checkNotNull();

        return new MappedStatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), this.mapping, this.mappingDefaultSupplier);
    }

    @Override
    public @NotNull SeasonKey<K, U> season() {
        this.checkNotNull();

        return new MappedSeasonKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), seasonKeyMapping, this.mapping, this.mappingDefaultSupplier);
    }

    @Override
    public @NotNull StageKey<K, U> stage(UnaryOperator<KeyFunction<K>> remapFunction) {
        this.checkNotNull();

        return new MappedStageKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), seasonKeyMapping, this.mapping, this.mappingDefaultSupplier, remapFunction);
    }

    protected void checkNotNull() {
        Objects.requireNonNull(this.mapping, "mapping");
        Objects.requireNonNull(this.defaultSupplier, "defaultValue");
        Objects.requireNonNull(this.mappingDefaultSupplier, "mappingDefaultSupplier");
    }
}
