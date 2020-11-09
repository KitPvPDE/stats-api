package net.kitpvp.stats.builder;

import org.jetbrains.annotations.NotNull;

public interface ComponentBuilder<V> {

    @NotNull V build();

}
