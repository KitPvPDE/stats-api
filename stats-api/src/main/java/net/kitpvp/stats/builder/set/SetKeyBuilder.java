package net.kitpvp.stats.builder.set;

import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.builder.keys.KeyBuilder;
import net.kitpvp.stats.keys.impl.set.SetSeasonKeyImpl;
import net.kitpvp.stats.keys.impl.set.SetStageKeyImpl;
import net.kitpvp.stats.keys.impl.set.SetStatsKeyImpl;
import net.kitpvp.stats.keys.set.SetSeasonKey;
import net.kitpvp.stats.keys.set.SetStageKey;
import net.kitpvp.stats.keys.set.SetStatsKey;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SetKeyBuilder<K, X> implements StatsKeyBuilder<K, Set<X>> {

    private final KeyBuilder<K> keyBuilder = new KeyBuilder<>();
    private Supplier<Set<X>> def = HashSet::new;

    public SetKeyBuilder<K, X> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public SetKeyBuilder<K, X> defaultValue(Supplier<Set<X>> constructor) {
        this.def = constructor;
        return this;
    }

    @Override
    public @NotNull SetStatsKey<K, X> build() {
        return new SetStatsKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull SetSeasonKey<K, X> season() {
        return new SetSeasonKeyImpl<>(SetStatsKeyImpl::new, this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull SetStageKey<K, X> stage() {
        return new SetStageKeyImpl<>(SetStatsKeyImpl::new, this.def, this.keyBuilder.build());
    }
}
