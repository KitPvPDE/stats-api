package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsWriter;

@FunctionalInterface
public interface Encoder<T> {

    StatsWriter encode(T t, StatsWriter statsWriter);


}
