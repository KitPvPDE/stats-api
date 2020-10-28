package net.kitpvp.api.stats.builder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import net.kitpvp.api.stats.builder.inc.IncBuilder;
import net.kitpvp.api.stats.keys.StatsKey;
import net.kitpvp.impl.stats.keys.StatsKeyImpl;
import net.kitpvp.impl.stats.keys.functions.FinalSupplier;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NormalBuilder<K, V> implements Builder<StatsKey<K, V>> {

    private final KeyBuilder<K> keyBuilder;
    private final ValueBuilder<V> valueBuilder;
    private Supplier<V> defaultSupplier;

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

    public NormalBuilder<K, V> valueBuilder(Consumer<ValueBuilder<V>> consumer) {
        consumer.accept(this.valueBuilder);
        return this;
    }

    @Override
    public StatsKey<K, V> build() {
        return new StatsKeyImpl<>(this.defaultSupplier, this.keyBuilder.build(), this.valueBuilder.build());
    }
}
