package net.kitpvp.stats.keys;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.api.builder.VoidStatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class VoidBuilder<V> implements VoidStatsKeyBuilder<V> {

    protected final VoidKeyBuilder keyBuilder;
    protected Supplier<V> defaultSupplier;

    public VoidBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
        this.defaultSupplier = () -> null;
    }

    public VoidBuilder<V> defaultNull() {
        this.defaultSupplier = () -> null;
        return this;
    }

    public VoidBuilder<V> defaultValue(V defaultValue) {
        this.defaultSupplier = new FinalSupplier<>(defaultValue);
        return this;
    }

    public VoidBuilder<V> defaultValue(Supplier<V> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
        return this;
    }

    public VoidBuilder<V> keyBuilder(Consumer<VoidKeyBuilder> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    @Override
    public @NotNull VoidStatsKey<V> build() {
        this.checkNotNull();

        return new VoidStatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }

    @Override
    public @NotNull VoidSeasonKey<V> season() {
        this.checkNotNull();

        return new NormalVoidSeasonKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }

    @Override
    public @NotNull VoidStageKey<V> stage(UnaryOperator<VoidKeyFunction> remapFunction) {
        this.checkNotNull();

        return new NormalVoidStageKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), remapFunction);
    }

    protected void checkNotNull() {
        Preconditions.checkNotNull(this.defaultSupplier, "defaultValue");
    }
}
