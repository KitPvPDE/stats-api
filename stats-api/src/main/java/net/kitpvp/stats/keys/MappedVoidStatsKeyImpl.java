package net.kitpvp.stats.keys;

import net.kitpvp.stats.reader.Reader;

import java.util.function.Function;
import java.util.function.Supplier;

public class MappedVoidStatsKeyImpl<V, U> implements VoidStatsKey<U> {

    private final Supplier<U> def;
    private final VoidKeyFunction key;
    private final Function<V, U> mapping;
    private final Supplier<V> mappingDef;

    public MappedVoidStatsKeyImpl(Supplier<U> def, VoidKeyFunction key, Function<V, U> mapping, Supplier<V> mappingDef) {
        this.def = def;
        this.key = key;
        this.mapping = mapping;
        this.mappingDef = mappingDef;
    }

    @Override
    public VoidKeyFunction keyFunction() {
        return this.key;
    }

    @Override
    public String key(Void unused) {
        return this.key.key(null);
    }

    @Override
    public U apply(U u) {
        return u;
    }

    @Override
    public U def() {
        return this.def.get();
    }

    @Override
    public U extract(Reader statsReader, Void key) {
        U result = this.mapping.apply(statsReader.find(this.key(key), this.mappingDef.get()));
        return result == null ? this.def() : result;
    }
}
