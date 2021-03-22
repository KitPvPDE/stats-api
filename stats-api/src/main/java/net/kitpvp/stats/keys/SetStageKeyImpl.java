package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Supplier;

class SetStageKeyImpl<K, X> extends StageKeyImpl<K, Set<X>, SetStatsKey<K, X>> implements SetStageKey<K, X> {

    SetStageKeyImpl(@Nullable Supplier<Set<X>> defaultFunction, @NotNull KeyFunction<K> keyFunction) {
        super(defaultFunction, keyFunction);
    }

    SetStageKeyImpl(@NotNull KeyFunction<K> keyFunction) {
        super(keyFunction);
    }

    @Override
    public SetVoidStageKey<X> bind(K k) {
        return new SetVoidStageKeyImpl<>(this.defaultFunction, this.keyFunction.bind(k));
    }

    @Override
    protected SetStatsKey<K, X> createKey(KeyFunction<K> function) {
        return new SetStatsKeyImpl<>(this.defaultFunction, function);
    }
}
