package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class NormalSeasonKeyImpl<K, V> extends SeasonKeyImpl<K, V, StatsKeyImpl<K, V>> {

    public NormalSeasonKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping) {
        super(defaultFunction, keyFunction, seasonKeyMapping);
    }

    @Override
    public Stream<? extends StatsKey<K, V>> stream() {
        return Stream.of(this.alltime(), this.season());
    }

    @Override
    public VoidSeasonKey<V> bind(K k) {
        return new NormalVoidSeasonKeyImpl<>(this.defaultFunction, KeyFunctions.bind(this.keyFunction, k));
    }

    @Override
    protected StatsKeyImpl<K, V> createKey(KeyFunction<K> function) {
        return new StatsKeyImpl<>(this.defaultFunction, function);
    }
}
