package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.keys.array.ArraySStageKey;
import net.kitpvp.stats.keys.array.ArraySStatsKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayVoidStageKeyImpl<X> extends VoidStageKeyImpl<List<X>, ArraySStatsKey<X>> implements ArraySStageKey<X> {

    public ArrayVoidStageKeyImpl(@Nullable BiFunction<Supplier<List<X>>, KeyFunction<Void>, ArraySStatsKey<X>> keyConstructor, @Nullable Supplier<List<X>> defaultFunction, @NotNull VoidKeyFunction keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
    }

    public ArrayVoidStageKeyImpl(@NotNull VoidKeyFunction keyFunction) {
        super(keyFunction);
    }

    @Override
    protected ArraySStatsKey<X> createKey(int season) {
        return new ArrayVoidStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season));
    }

    @Override
    protected ArraySStatsKey<X> createKey(int season, int stage) {
        return new ArrayVoidStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season, stage));
    }
}
