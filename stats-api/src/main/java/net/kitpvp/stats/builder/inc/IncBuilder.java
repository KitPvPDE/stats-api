package net.kitpvp.stats.builder.inc;

import net.kitpvp.stats.builder.builders.KeyBuilder;
import net.kitpvp.stats.builder.builders.NormalBuilder;
import net.kitpvp.stats.keys.impl.inc.IncStatsKeyImpl;
import net.kitpvp.stats.keys.inc.IncStatsKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

public class IncBuilder<K, V> extends NormalBuilder<K, V> {

    private BiFunction<V, V, V> sum;
    private V neutral, offset;
    ;

    public IncBuilder(KeyBuilder<K> keyBuilder, BiFunction<V, V, V> sum, V neutral) {
        super(keyBuilder, null);
        this.sum = sum;
        this.neutral = this.offset = neutral;
    }

    public IncBuilder<K, V> offset(V offset) {
        this.offset = offset;
        return this;
    }

    public IncBuilder<K, V> neutral(V neutral) {
        this.neutral = neutral;
        return this;
    }

    @Override
    public @NotNull IncStatsKey<K, V> build() {
        return new IncStatsKeyImpl<>(this.keyBuilder.build(), this.sum, this.neutral, this.offset);
    }
}
