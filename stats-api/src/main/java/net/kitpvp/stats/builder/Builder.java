package net.kitpvp.stats.builder;

import org.jetbrains.annotations.NotNull;

public interface Builder<V> {

    @NotNull V build();

}
