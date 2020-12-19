package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;

public interface Context<T> extends Encoder<T>, Decoder<T> {

    @Override
    T decode(StatsReader statsReader);

    @Override
    StatsWriter encode(T t, StatsWriter statsWriter);
}
