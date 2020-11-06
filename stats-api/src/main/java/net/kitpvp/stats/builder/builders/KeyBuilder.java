package net.kitpvp.stats.builder.builders;

import net.kitpvp.stats.builder.Builder;

import java.util.function.Function;

public class KeyBuilder<K> implements Builder<Function<K, String>> {

    @Override
    public Function<K, String> build() {
        return null;
    }
}
