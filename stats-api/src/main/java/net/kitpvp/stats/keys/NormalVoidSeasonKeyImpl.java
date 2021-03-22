package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import java.util.stream.Stream;

class NormalVoidSeasonKeyImpl<V> extends VoidSeasonKeyImpl<V, VoidStatsKeyImpl<V>> {

    public NormalVoidSeasonKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull VoidKeyFunction keyFunction) {
        super(defaultFunction, keyFunction);
    }

    @Override
    public Stream<? extends VoidStatsKey<V>> stream() {
        return Stream.of(this.alltime(), this.season());
    }

    @Override
    protected VoidStatsKeyImpl<V> createKey(VoidKeyFunction function) {
        return new VoidStatsKeyImpl<>(this.defaultFunction, function);
    }
}
