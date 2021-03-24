package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;

public interface Codec<T, W extends StatsWriter> extends Encoder<T, W>, Decoder<T> {

    @Override
    T decode(StatsReader statsReader);

    @Override
    void encode(T t, W statsWriter);
}
