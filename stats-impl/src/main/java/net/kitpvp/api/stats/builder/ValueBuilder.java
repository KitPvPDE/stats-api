package net.kitpvp.api.stats.builder;

import java.util.function.Function;

public class ValueBuilder<V> implements Builder<Function<V, V>> {

    @Override
    public Function<V, V> build() {
        return null;
    }
}
