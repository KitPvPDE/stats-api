package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.array.ArraySeasonKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArraySeasonKeyImpl<K, X> extends SeasonKeyImpl<K, List<X>, ArrayStatsKey<K, X>> implements ArraySeasonKey<K, X> {

    public ArraySeasonKeyImpl(@Nullable BiFunction<Supplier<List<X>>, KeyFunction<K>, ArrayStatsKey<K, X>> keyConstructor, @Nullable Supplier<List<X>> defaultFunction, @NotNull KeyFunction<K> keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
    }

    public ArraySeasonKeyImpl(@NotNull KeyFunction<K> keyFunction) {
        super(keyFunction);
    }

    @Override
    protected ArrayStatsKey<K, X> createKey(int season) {
        return new ArrayStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season));
    }
}
