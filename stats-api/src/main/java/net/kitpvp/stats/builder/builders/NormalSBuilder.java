package net.kitpvp.stats.builder.builders;

import net.kitpvp.stats.builder.Builder;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.keys.impl.StatsKeyImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class NormalSBuilder<V> extends Builder<Void, V> {

    public NormalSBuilder(KeyBuilder<Void> keyBuilder, Supplier<V> defaultSupplier) {
        super(keyBuilder, defaultSupplier);
    }

    @Override
    public @NotNull StatsKey<Void, V> build() {
        return new StatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }
}
