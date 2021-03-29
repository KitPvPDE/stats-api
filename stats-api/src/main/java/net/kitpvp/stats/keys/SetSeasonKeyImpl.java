package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Supplier;

class SetSeasonKeyImpl<K, X> extends SeasonKeyImpl<K, Set<X>, SetStatsKey<K, X>> implements SetSeasonKey<K, X> {

    SetSeasonKeyImpl(@Nullable Supplier<Set<X>> defaultFunction, @NotNull KeyFunction<K> keyFunction) {
        super(defaultFunction, keyFunction);
    }

    @Override
    public SetVoidSeasonKey<X> bind(K k) {
        return new SetVoidSeasonKeyImpl<>(this.defaultFunction, this.keyFunction.bind(k));
    }

    @Override
    protected SetStatsKey<K, X> createKey(KeyFunction<K> function) {
        return new SetStatsKeyImpl<>(this.defaultFunction, function);
    }
}
