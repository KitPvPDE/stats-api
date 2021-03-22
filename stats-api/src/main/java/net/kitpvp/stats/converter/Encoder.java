package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsWriter;

@FunctionalInterface
public interface Encoder<T> {

    <W extends StatsWriter> W encode(T t, W statsWriter);


}
