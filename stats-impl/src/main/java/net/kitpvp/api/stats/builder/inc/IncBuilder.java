package net.kitpvp.api.stats.builder.inc;

import net.kitpvp.api.stats.builder.NormalBuilder;
import net.kitpvp.api.stats.keys.inc.IncStatsKey;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class IncBuilder<K, V> extends NormalBuilder<K, V> {

    private BiFunction<V, V, V> sum;
    private V neutral, offset;
    ;

    public IncBuilder(Supplier<V> defaultSupplier, BiFunction<V, V, V> sum, V neutral) {
        super(defaultSupplier);
        this.sum = sum;
        this.neutral = this.offset = neutral;
    }

    public IncBuilder<K, V> offset(V offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public IncStatsKey<K, V> build() {
        return super.build();
    }
}
