package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

class ArrayVoidStageKeyImpl<X> extends VoidStageKeyImpl<List<X>, ArrayVoidStatsKey<X>> implements ArrayVoidStageKey<X> {

    ArrayVoidStageKeyImpl(@Nullable Supplier<List<X>> defaultFunction, @NotNull VoidKeyFunction keyFunction) {
        super(defaultFunction, keyFunction);
    }

    ArrayVoidStageKeyImpl(@NotNull VoidKeyFunction keyFunction) {
        super(keyFunction);
    }

    @Override
    protected ArrayVoidStatsKey<X> createKey(VoidKeyFunction function) {
        return new ArrayVoidStatsKeyImpl<>(this.defaultFunction, function);
    }
}
