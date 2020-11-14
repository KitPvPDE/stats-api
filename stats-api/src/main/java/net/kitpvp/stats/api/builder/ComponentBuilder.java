package net.kitpvp.stats.api.builder;

import org.jetbrains.annotations.NotNull;

public interface ComponentBuilder<V> {

    @NotNull V build();

}
