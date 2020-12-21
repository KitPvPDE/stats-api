package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;

public interface Codec<T, Writer extends StatsWriter> extends Encoder<T, Writer>, Decoder<T> {

    @Override
    T decode(StatsReader statsReader);

    @Override
    Writer encode(T t, Writer statsWriter);
}
