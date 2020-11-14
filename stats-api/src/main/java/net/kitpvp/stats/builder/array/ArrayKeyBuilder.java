package net.kitpvp.stats.builder.array;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.KeyBuilder;
import net.kitpvp.stats.keys.array.ArraySeasonKey;
import net.kitpvp.stats.keys.array.ArrayStageKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import net.kitpvp.stats.keys.impl.ArraySeasonKeyImpl;
import net.kitpvp.stats.keys.impl.ArrayStageKeyImpl;
import net.kitpvp.stats.keys.impl.ArrayStatsKeyImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ArrayKeyBuilder<K, X> implements StatsKeyBuilder<K, List<X>> {

    private final KeyBuilder<K> keyBuilder = new KeyBuilder<>();
    private Supplier<List<X>> def = ArrayList::new;

    public ArrayKeyBuilder<K, X> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
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
        return new ArraySeasonKeyImpl<>(ArrayStatsKeyImpl::new, this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull ArrayStageKey<K, X> stage() {
        return new ArrayStageKeyImpl<>(ArrayStatsKeyImpl::new, this.def, this.keyBuilder.build());
    }
}
