package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsWriter;

public interface Encoder<T> {

    StatsWriter encode(T t, StatsWriter statsWriter);


}
