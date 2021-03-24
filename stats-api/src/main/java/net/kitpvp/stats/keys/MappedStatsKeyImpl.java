package net.kitpvp.stats.keys;

import net.kitpvp.stats.reader.Reader;

import java.util.function.Function;
import java.util.function.Supplier;

public class MappedStatsKeyImpl<K, V, U> extends StatsKeyImpl<K, U> {

    private final Function<V, U> mapping;
    private final Supplier<V> mappingDef;

    public MappedStatsKeyImpl(Supplier<U> def, KeyFunction<K> key, Function<V, U> mapping, Supplier<V> mappingDef) {
        super(def, key);
        this.mapping = mapping;
        this.mappingDef = mappingDef;
    }

    @Override
    public VoidStatsKey<U> bind(K k) {
        return new MappedVoidStatsKeyImpl<>(this.def, this.keyFunction().bind(k), this.mapping, this.mappingDef);
    }

    @Override
    public U extract(Reader statsReader, K key) {
        U result = this.mapping.apply(statsReader.find(this.key(key), this.mappingDef.get()));
        return result == null ? this.def() : result;
    }
}
