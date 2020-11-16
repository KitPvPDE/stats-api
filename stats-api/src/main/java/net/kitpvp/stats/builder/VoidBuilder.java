package net.kitpvp.stats.builder;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.VoidKeyBuilder;
import net.kitpvp.stats.keys.SSeasonKey;
import net.kitpvp.stats.keys.SStageKey;
import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.impl.VoidSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.VoidStageKeyImpl;
import net.kitpvp.stats.keys.impl.VoidStatsKeyImpl;
import net.kitpvp.stats.keys.impl.functions.FinalSupplier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class VoidBuilder<V> implements StatsKeyBuilder<Void, V> {

    protected final VoidKeyBuilder keyBuilder;
    protected Supplier<V> defaultSupplier;

    public VoidBuilder() {
        this.keyBuilder = new VoidKeyBuilder();
        this.defaultSupplier = null;
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
    public @NotNull SStatsKey<V> build() {
        this.checkNotNull();

        return new VoidStatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }

    @Override
    public @NotNull SSeasonKey<V> season() {
        this.checkNotNull();

        return new VoidSeasonKeyImpl<V, SStatsKey<V>>(VoidStatsKeyImpl::new, this.defaultSupplier, this.keyBuilder.build());
    }

    @Override
    public @NotNull SStageKey<V> stage() {
        this.checkNotNull();

        return new VoidStageKeyImpl<>(VoidStatsKeyImpl::new, this.defaultSupplier, this.keyBuilder.build());
    }

    protected void checkNotNull() {
        Preconditions.checkNotNull(this.defaultSupplier, "defaultValue");
    }
}
