package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

abstract class VoidStageKeyImpl<V, S extends VoidStatsKey<V>> extends VoidSeasonKeyImpl<V, S> implements VoidStageKey<V> {

    private final UnaryOperator<VoidKeyFunction> remapFunction;
    private S key;

    VoidStageKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull VoidKeyFunction keyFunction, UnaryOperator<VoidKeyFunction> remapFunction) {
        super(defaultFunction, keyFunction);
        this.remapFunction = remapFunction;
    }

    VoidStageKeyImpl(@NotNull VoidKeyFunction keyFunction, UnaryOperator<VoidKeyFunction> remapFunction) {
        super(keyFunction);
        this.remapFunction = remapFunction;
    }

    @Override
    public S stage() {
        if(this.key == null) {
            key = this.createKey(this.remapFunction.apply(this.keyFunction));
        }
        return this.key;
    }

    protected abstract S createKey(VoidKeyFunction function);

}
