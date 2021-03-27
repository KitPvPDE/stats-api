package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsReader;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface Decoder<T> {

    @NotNull T decode(StatsReader statsReader);
}
