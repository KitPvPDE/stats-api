package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsReader;

@FunctionalInterface
public interface Decoder<T> {

    T decode(StatsReader statsReader);
}
