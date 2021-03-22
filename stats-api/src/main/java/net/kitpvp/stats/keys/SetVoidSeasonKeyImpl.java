package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Supplier;

class SetVoidSeasonKeyImpl<X> extends VoidSeasonKeyImpl<Set<X>, SetVoidStatsKey<X>> implements SetVoidSeasonKey<X> {

    SetVoidSeasonKeyImpl(@Nullable Supplier<Set<X>> defaultFunction, @NotNull VoidKeyFunction keyFunction) {
        super(defaultFunction, keyFunction);
    }

    @Override
    protected SetVoidStatsKey<X> createKey(VoidKeyFunction function) {
        return new SetVoidStatsKeyImpl<>(this.defaultFunction, function);
    }
}
