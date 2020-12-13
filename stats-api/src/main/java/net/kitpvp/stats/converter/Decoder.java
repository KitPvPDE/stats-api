package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsReader;

public interface Decoder<T> {

    T decode(StatsReader statsReader);
}
