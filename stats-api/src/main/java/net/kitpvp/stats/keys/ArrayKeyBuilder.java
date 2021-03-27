package net.kitpvp.stats.keys;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ArrayKeyBuilder<K, X> implements StatsKeyBuilder<K, List<X>> {

    public static <K, X> ArrayKeyBuilder<K, X> builder() {
        return new ArrayKeyBuilder<>();
    }

    private final KeyBuilder<K> keyBuilder = new KeyBuilder<>();
    private Supplier<List<X>> def = ArrayList::new;

    public ArrayKeyBuilder<K, X> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public ArrayKeyBuilder<K, X> defaultNull() {
        this.def = () -> null;
        return this;
    }

    public ArrayKeyBuilder<K, X> defaultValue(Supplier<List<X>> constructor) {
        this.def = constructor;
        return this;
    }

    @Override
    public @NotNull ArrayStatsKey<K, X> build() {
        return new ArrayStatsKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull ArraySeasonKey<K, X> season() {
        return new ArraySeasonKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull ArrayStageKey<K, X> stage() {
        return new ArrayStageKeyImpl<>(this.def, this.keyBuilder.build());
    }
}
