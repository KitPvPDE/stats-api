package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.array.ArraySSeasonKey;
import net.kitpvp.stats.keys.array.ArraySStatsKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayVoidSeasonKeyImpl<X> extends VoidSeasonKeyImpl<List<X>, ArraySStatsKey<X>> implements ArraySSeasonKey<X> {

    public ArrayVoidSeasonKeyImpl(@Nullable BiFunction<Supplier<List<X>>, KeyFunction<Void>, ArraySStatsKey<X>> keyConstructor, @Nullable Supplier<List<X>> defaultFunction, @NotNull KeyFunction<Void> keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
    }

    public ArrayVoidSeasonKeyImpl(@NotNull KeyFunction<Void> keyFunction) {
        super(keyFunction);
    }

    @Override
    protected ArraySStatsKey<X> createKey(int season) {
        return new ArrayVoidStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season));
    }
}
