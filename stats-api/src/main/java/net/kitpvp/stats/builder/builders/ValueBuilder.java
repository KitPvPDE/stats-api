package net.kitpvp.stats.builder.builders;

import net.kitpvp.stats.builder.ComponentBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class ValueBuilder<V> implements ComponentBuilder<Function<V, V>> {

    @Override
    public @NotNull Function<V, V> build() {
        return null;
    }
}
