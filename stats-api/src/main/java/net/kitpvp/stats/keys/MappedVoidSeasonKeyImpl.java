package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

class MappedVoidSeasonKeyImpl<V, U> extends VoidSeasonKeyImpl<U, VoidStatsKey<U>> {

    private final Function<V, U> mapping;
    private final Supplier<V> mappingDef;

    public MappedVoidSeasonKeyImpl(@Nullable Supplier<U> defaultFunction, @NotNull VoidKeyFunction keyFunction, Function<V, U> mapping, Supplier<V> mappingDef) {
        super(defaultFunction, keyFunction);
        this.mapping = mapping;
        this.mappingDef = mappingDef;
    }

    @Override
    public Stream<? extends VoidStatsKey<U>> stream() {
        return Stream.of(this.alltime(), this.season());
    }

    @Override
    protected VoidStatsKey<U> createKey(VoidKeyFunction function) {
        return new MappedVoidStatsKeyImpl<>(this.defaultFunction, function, this.mapping, this.mappingDef);
    }
}
