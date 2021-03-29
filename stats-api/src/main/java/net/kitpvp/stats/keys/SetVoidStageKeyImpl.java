package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class SetVoidStageKeyImpl<X> extends VoidStageKeyImpl<Set<X>, SetVoidStatsKey<X>> implements SetVoidStageKey<X> {

    SetVoidStageKeyImpl(@Nullable Supplier<Set<X>> defaultFunction, @NotNull VoidKeyFunction keyFunction, UnaryOperator<VoidKeyFunction> remapFunction) {
        super(defaultFunction, keyFunction, remapFunction);
    }

    @Override
    protected SetVoidStatsKey<X> createKey(VoidKeyFunction function) {
        return new SetVoidStatsKeyImpl<>(this.defaultFunction, function);
    }
}
