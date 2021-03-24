package net.kitpvp.stats.keys;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class MappedSeasonKeyImpl<K, V, U> extends SeasonKeyImpl<K, U, MappedStatsKeyImpl<K, V, U>> {

    private final Function<V, U> mapping;
    private final Supplier<V> mappingDef;

    public MappedSeasonKeyImpl(@Nullable Supplier<U> defaultFunction, @NotNull KeyFunction<K> keyFunction, Function<V, U> mapping, Supplier<V> mappingDef) {
        super(defaultFunction, keyFunction);
        this.mapping = mapping;
        this.mappingDef = mappingDef;
    }

    @Override
    public Stream<? extends StatsKey<K, U>> stream() {
        return Stream.of(this.alltime(), this.season());
    }

    @Override
    public VoidSeasonKey<U> bind(K k) {
        return new MappedVoidSeasonKeyImpl<>(this.defaultFunction, this.keyFunction.bind(k), this.mapping, this.mappingDef);
    }

    @Override
    protected MappedStatsKeyImpl<K, V, U> createKey(KeyFunction<K> function) {
        return new MappedStatsKeyImpl<>(this.defaultFunction, function, this.mapping, this.mappingDef);
    }
}
