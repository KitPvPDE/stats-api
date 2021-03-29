package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class SetStageKeyImpl<K, X> extends StageKeyImpl<K, Set<X>, SetStatsKey<K, X>> implements SetStageKey<K, X> {

    SetStageKeyImpl(@Nullable Supplier<Set<X>> defaultFunction, @NotNull KeyFunction<K> keyFunction, UnaryOperator<KeyFunction<K>> remapFunction) {
        super(defaultFunction, keyFunction, remapFunction);
    }

    @Override
    public SetVoidStageKey<X> bind(K k) {
        return new SetVoidStageKeyImpl<>(this.defaultFunction, this.keyFunction.bind(k),
                function -> this.remapFunction.apply(this.keyFunction).bind(k));
    }

    @Override
    protected SetStatsKey<K, X> createKey(KeyFunction<K> function) {
        return new SetStatsKeyImpl<>(this.defaultFunction, function);
    }
}
