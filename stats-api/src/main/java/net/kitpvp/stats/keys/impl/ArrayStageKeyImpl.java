package net.kitpvp.stats.keys.impl;

import net.kitpvp.stats.keys.array.ArrayStageKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayStageKeyImpl<K, X> extends StageKeyImpl<K, List<X>, ArrayStatsKey<K, X>> implements ArrayStageKey<K, X> {

    public ArrayStageKeyImpl(@Nullable BiFunction<Supplier<List<X>>, Function<K, String>, ArrayStatsKey<K, X>> keyConstructor, @Nullable Supplier<List<X>> defaultFunction, @NotNull Function<K, String> keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
    }

    public ArrayStageKeyImpl(@NotNull Function<K, String> keyFunction) {
        super(keyFunction);
    }

    @Override
    protected ArrayStatsKey<K, X> createKey(int season) {
        return new ArrayStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season));
    }

    @Override
    protected ArrayStatsKey<K, X> createKey(int season, int stage) {
        return new ArrayStatsKeyImpl<>(this.defaultFunction, this.createKeyFunction(season, stage));
    }
}
