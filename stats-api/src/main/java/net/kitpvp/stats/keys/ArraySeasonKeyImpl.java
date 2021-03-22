package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

class ArraySeasonKeyImpl<K, X> extends SeasonKeyImpl<K, List<X>, ArrayStatsKey<K, X>> implements ArraySeasonKey<K, X> {

    ArraySeasonKeyImpl(@Nullable Supplier<List<X>> defaultFunction, @NotNull KeyFunction<K> keyFunction) {
        super(defaultFunction, keyFunction);
    }

    ArraySeasonKeyImpl(@NotNull KeyFunction<K> keyFunction) {
        super(keyFunction);
    }

    @Override
    public ArrayVoidSeasonKey<X> bind(K k) {
        return new ArrayVoidSeasonKeyImpl<>(this.defaultFunction, KeyFunctions.bind(this.keyFunction, k));
    }

    @Override
    protected ArrayStatsKey<K, X> createKey(KeyFunction<K> function) {
        return new ArrayStatsKeyImpl<>(this.defaultFunction, function);
    }
}
