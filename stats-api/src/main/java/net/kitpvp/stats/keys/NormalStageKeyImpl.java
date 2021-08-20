package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class NormalStageKeyImpl<K, V> extends StageKeyImpl<K, V, StatsKey<K, V>> {

    public NormalStageKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, UnaryOperator<KeyFunction<K>> remapFunction) {
        super(defaultFunction, keyFunction, seasonKeyMapping, remapFunction);
    }

    @Override
    public Stream<? extends StatsKey<K, V>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }

    @Override
    public VoidStageKey<V> bind(K k) {
        return new NormalVoidStageKeyImpl<>(this.defaultFunction, this.keyFunction.bind(k),
                (function) -> this.remapFunction.apply(this.keyFunction).bind(k));
    }

    @Override
    protected StatsKey<K, V> createKey(KeyFunction<K> function) {
        return new StatsKeyImpl<>(this.defaultFunction, function);
    }
}
