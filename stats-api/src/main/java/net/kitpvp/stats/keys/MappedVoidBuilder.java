package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.builder.VoidStatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MappedVoidBuilder<V, U> implements VoidStatsKeyBuilder<U> {
    private final VoidKeyBuilder keyBuilder;
    private final Function<V, U> mapping;
    private Supplier<V> mappingDefaultSupplier;
    private Supplier<U> defaultSupplier;

    MappedVoidBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
        this.mapping = null;
        this.defaultSupplier = () -> null;
    }

    MappedVoidBuilder(Function<V, U> mapping) {
        this.keyBuilder = new VoidKeyBuilder();
        this.mapping = mapping;
        this.defaultSupplier = () -> null;
    }

    MappedVoidBuilder(VoidKeyBuilder keyBuilder, Function<V, U> mapping) {
        this.keyBuilder = keyBuilder;
        this.mapping = mapping;
        this.defaultSupplier = () -> null;
    }

    public <T, R> MappedVoidBuilder<T, R> mapping(Function<T, R> mapping) {
        return new MappedVoidBuilder<>(this.keyBuilder, mapping);
    }

    public MappedVoidBuilder<V, U> defaultNull() {
        this.defaultSupplier = () -> null;
        return this;
    }

    public MappedVoidBuilder<V, U> defaultValue(U defaultValue) {
        this.defaultSupplier = new FinalSupplier<>(defaultValue);
        return this;
    }

    public MappedVoidBuilder<V, U> defaultValue(Supplier<U> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
        return this;
    }

    public MappedVoidBuilder<V, U> defaultMappingValue(V mappingDefaultSupplier) {
        this.mappingDefaultSupplier = new FinalSupplier<>(mappingDefaultSupplier);
        return this;
    }

    public MappedVoidBuilder<V, U> defaultMappingValue(Supplier<V> mappingDefaultSupplier) {
        this.mappingDefaultSupplier = mappingDefaultSupplier;
        return this;
    }

    public MappedVoidBuilder<V, U> keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    @Override
    public @NotNull VoidStatsKey<U> build() {
        this.checkNotNull();

        return new MappedVoidStatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), this.mapping, this.mappingDefaultSupplier);
    }

    @Override
    public @NotNull VoidSeasonKey<U> season() {
        this.checkNotNull();

        return new MappedVoidSeasonKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), this.mapping, this.mappingDefaultSupplier);
    }

    @Override
    public @NotNull VoidStageKey<U> stage(UnaryOperator<VoidKeyFunction> remapFunction) {
        this.checkNotNull();

        return new MappedVoidStageKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), this.mapping, this.mappingDefaultSupplier, remapFunction);
    }

    protected void checkNotNull() {
        Objects.requireNonNull(this.mapping, "mapping");
        Objects.requireNonNull(this.defaultSupplier, "defaultValue");
        Objects.requireNonNull(this.mappingDefaultSupplier, "mappingDefaultSupplier");
    }
}
