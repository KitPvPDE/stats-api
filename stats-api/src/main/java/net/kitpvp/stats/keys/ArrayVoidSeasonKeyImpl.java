package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

class ArrayVoidSeasonKeyImpl<X> extends VoidSeasonKeyImpl<List<X>, ArrayVoidStatsKey<X>> implements ArrayVoidSeasonKey<X> {

    ArrayVoidSeasonKeyImpl(@Nullable Supplier<List<X>> defaultFunction, @NotNull VoidKeyFunction keyFunction) {
        super(defaultFunction, keyFunction);
    }

    @Override
    protected ArrayVoidStatsKey<X> createKey(VoidKeyFunction function) {
        return new ArrayVoidStatsKeyImpl<>(this.defaultFunction, function);
    }
}
