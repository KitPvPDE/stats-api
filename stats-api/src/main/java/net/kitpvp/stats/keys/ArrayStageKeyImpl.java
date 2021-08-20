package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class ArrayStageKeyImpl<K, X> extends StageKeyImpl<K, List<X>, ArrayStatsKey<K, X>> implements ArrayStageKey<K, X> {

    public ArrayStageKeyImpl(@Nullable Supplier<List<X>> defaultFunction, @NotNull KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, UnaryOperator<KeyFunction<K>> remapFunction) {
        super(defaultFunction, keyFunction, seasonKeyMapping, remapFunction);
    }

    @Override
    public ArrayVoidStageKey<X> bind(K k) {
        return new ArrayVoidStageKeyImpl<>(this.defaultFunction, this.keyFunction.bind(k),
                (function) -> this.remapFunction.apply(this.keyFunction).bind(k));
    }

    @Override
    protected ArrayStatsKey<K, X> createKey(KeyFunction<K> function) {
        return new ArrayStatsKeyImpl<>(this.defaultFunction, function);
    }
}
