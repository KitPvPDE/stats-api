package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsWriter;

@FunctionalInterface
public interface Encoder<T, Writer extends StatsWriter> {

    Writer encode(T t, Writer statsWriter);


}
