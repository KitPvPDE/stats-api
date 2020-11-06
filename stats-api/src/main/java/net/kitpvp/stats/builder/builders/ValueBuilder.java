package net.kitpvp.stats.builder.builders;

import net.kitpvp.stats.builder.Builder;

import java.util.function.Function;

public class ValueBuilder<V> implements Builder<Function<V, V>> {

    @Override
    public Function<V, V> build() {
        return null;
    }
}
