package net.kitpvp.stats.builder.builders;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.kitpvp.stats.builder.Builder;
import net.kitpvp.stats.builder.inc.IncBuilder;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.keys.impl.functions.FinalSupplier;
import net.kitpvp.stats.keys.impl.StatsKeyImpl;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NormalBuilder<K, V> implements Builder<StatsKey<K, V>> {

    protected final KeyBuilder<K> keyBuilder;
    protected Supplier<V> defaultSupplier;

    /*
    public IncBuilder<K, Long> defaultValue(long def) {
        return new IncBuilder<>(def, Long::sum, 0L);
    }

    public IncBuilder<K, Integer> defaultValue(int def) {
        return new IncBuilder<>(def, Integer::sum, 0);
    }

    public IncBuilder<K, V> defaultValue(V def, BiFunction<V, V, V> sum, V neutral) {
        return new IncBuilder<>(def, sum, neutral);
    }*/

    public IncBuilder<K, Integer> defaultValue(int def) {
        return new IncBuilder<>(this.keyBuilder, Integer::sum, 0);
    }

    public NormalBuilder<K, V> defaultValue(V defaultValue) {
        this.defaultSupplier = new FinalSupplier<>(defaultValue);
        return this;
    }

    public NormalBuilder<K, V> defaultValue(Supplier<V> defaultSupplier) {
        this.defaultSupplier = defaultSupplier;
        return this;
    }

    public NormalBuilder<K, V> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    @Override
    public @NotNull StatsKey<K, V> build() {
        return new StatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build());
    }
}
