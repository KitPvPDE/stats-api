package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class MappedStageKeyImpl<K, V, U> extends StageKeyImpl<K, U, StatsKey<K, U>> {

    private final Function<V, U> mapping;
    private final Supplier<V> mappingDef;

    public MappedStageKeyImpl(@Nullable Supplier<U> defaultFunction, @NotNull KeyFunction<K> keyFunction, Function<V, U> mapping, Supplier<V> mappingDef, UnaryOperator<KeyFunction<K>> remapFunction) {
        super(defaultFunction, keyFunction, remapFunction);
        this.mapping = mapping;
        this.mappingDef = mappingDef;
    }

    @Override
    public Stream<? extends StatsKey<K, U>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }

    @Override
    public VoidStageKey<U> bind(K k) {
        return new MappedVoidStageKeyImpl<>(this.defaultFunction, this.keyFunction.bind(k), this.mapping, this.mappingDef,
                function -> this.remapFunction.apply(this.keyFunction).bind(k));
    }

    @Override
    protected StatsKey<K, U> createKey(KeyFunction<K> function) {
        return new MappedStatsKeyImpl<>(this.defaultFunction, function, this.mapping, this.mappingDef);
    }
}
