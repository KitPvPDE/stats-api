package net.kitpvp.stats.converter;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import org.jetbrains.annotations.NotNull;

public interface Codec<T, W extends StatsWriter> extends Encoder<T, W>, Decoder<T> {

    @Override
    @NotNull
    T decode(StatsReader statsReader);

    @Override
    void encode(T t, W statsWriter);
}
