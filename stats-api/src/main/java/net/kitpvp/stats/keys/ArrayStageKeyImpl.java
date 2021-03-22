package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

class ArrayStageKeyImpl<K, X> extends StageKeyImpl<K, List<X>, ArrayStatsKey<K, X>> implements ArrayStageKey<K, X> {

    ArrayStageKeyImpl(@Nullable Supplier<List<X>> defaultFunction, @NotNull KeyFunction<K> keyFunction) {
        super(defaultFunction, keyFunction);
    }

    @Override
    public ArrayVoidStageKey<X> bind(K k) {
        return new ArrayVoidStageKeyImpl<>(this.defaultFunction, this.keyFunction.bind(k));
    }

    @Override
    protected ArrayStatsKey<K, X> createKey(KeyFunction<K> function) {
        return new ArrayStatsKeyImpl<>(this.defaultFunction, function);
    }
}
