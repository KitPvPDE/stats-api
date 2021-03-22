package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;

public interface Codec<T> extends Encoder<T>, Decoder<T> {

    @Override
    T decode(StatsReader statsReader);

    @Override
    <W extends StatsWriter> W encode(T t, W statsWriter);
}
