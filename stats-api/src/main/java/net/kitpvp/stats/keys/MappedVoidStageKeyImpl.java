package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class MappedVoidStageKeyImpl<V, U> extends VoidStageKeyImpl<U, VoidStatsKey<U>> {

    private final Function<V, U> mapping;
    private final Supplier<V> mappingDef;

    public MappedVoidStageKeyImpl(@Nullable Supplier<U> defaultFunction, @NotNull VoidKeyFunction keyFunction, Function<V, U> mapping, Supplier<V> mappingDef, UnaryOperator<VoidKeyFunction> remapFunction) {
        super(defaultFunction, keyFunction, remapFunction);
        this.mapping = mapping;
        this.mappingDef = mappingDef;
    }

    @Override
    public Stream<? extends VoidStatsKey<U>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }

    @Override
    protected VoidStatsKey<U> createKey(VoidKeyFunction function) {
        return new MappedVoidStatsKeyImpl<>(this.defaultFunction, function, this.mapping, this.mappingDef);
    }
}
