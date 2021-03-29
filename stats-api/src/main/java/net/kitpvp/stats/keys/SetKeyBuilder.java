package net.kitpvp.stats.keys;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SetKeyBuilder<K, X> implements StatsKeyBuilder<K, Set<X>> {

    private final KeyBuilder<K> keyBuilder = new KeyBuilder<>();
    private Supplier<Set<X>> def = HashSet::new;

    public SetKeyBuilder<K, X> keyBuilder(Consumer<KeyBuilder<K>> consumer) {
        consumer.accept(this.keyBuilder);
        return this;
    }

    public SetKeyBuilder<K, X> defaultNull() {
        this.def = () -> null;
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
        return new SetSeasonKeyImpl<>(this.def, this.keyBuilder.build());
    }

    @Override
    public @NotNull SetStageKey<K, X> stage(UnaryOperator<KeyFunction<K>> remapFunction) {
        return new SetStageKeyImpl<>(this.def, this.keyBuilder.build(), remapFunction);
    }
}
