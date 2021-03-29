package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class NormalVoidStageKeyImpl<V> extends VoidStageKeyImpl<V, VoidStatsKeyImpl<V>> {

    public NormalVoidStageKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull VoidKeyFunction keyFunction, UnaryOperator<VoidKeyFunction> remapFunction) {
        super(defaultFunction, keyFunction, remapFunction);
    }

    @Override
    public Stream<? extends VoidStatsKey<V>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }

    @Override
    protected VoidStatsKeyImpl<V> createKey(VoidKeyFunction function) {
        return new VoidStatsKeyImpl<>(this.defaultFunction, function);
    }
}
