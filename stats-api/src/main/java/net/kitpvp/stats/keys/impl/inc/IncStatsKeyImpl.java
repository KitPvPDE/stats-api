package net.kitpvp.stats.keys.impl.inc;

import net.kitpvp.stats.keys.impl.StatsKeyImpl;
import net.kitpvp.stats.keys.impl.functions.FinalSupplier;
import net.kitpvp.stats.keys.inc.IncStatsKey;

import java.util.function.BiFunction;
import java.util.function.Function;

public class IncStatsKeyImpl<K, V> extends StatsKeyImpl<K, V> implements IncStatsKey<K, V> {

    private final BiFunction<V, V, V> function;
    private final V neutral, offset;

    public IncStatsKeyImpl(Function<K, String> toKey, BiFunction<V, V, V> function, V neutral, V offset) {
        super(null, toKey);
        this.function = function;
        this.neutral = neutral;
        this.offset = offset;
    }

    @Override
    public V apply(V v) {
        return this.function.apply(this.offset, v);
    }

    @Override
    public V neutral() {
        return this.neutral;
    }

    @Override
    public V def() {
        return this.neutral();
    }

    @Override
    public BiFunction<V, V, V> function() {
        return this.function;
    }
}
