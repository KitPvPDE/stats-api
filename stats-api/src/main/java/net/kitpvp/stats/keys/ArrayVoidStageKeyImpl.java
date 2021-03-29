package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class ArrayVoidStageKeyImpl<X> extends VoidStageKeyImpl<List<X>, ArrayVoidStatsKey<X>> implements ArrayVoidStageKey<X> {

    public ArrayVoidStageKeyImpl(@Nullable Supplier<List<X>> defaultFunction, @NotNull VoidKeyFunction keyFunction, UnaryOperator<VoidKeyFunction> remapFunction) {
        super(defaultFunction, keyFunction, remapFunction);
    }

    @Override
    protected ArrayVoidStatsKey<X> createKey(VoidKeyFunction function) {
        return new ArrayVoidStatsKeyImpl<>(this.defaultFunction, function);
    }
}
