package net.kitpvp.stats.converter;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;

@RequiredArgsConstructor
public abstract class Converter<T> implements Codec<T> {

    protected final Decoder<T> decode;
    protected final Encoder<T> encode;

    public Converter(Codec<T> context) {
        this(context, context);
    }

    @Override
    public final T decode(StatsReader statsReader) {
        return this.decode.decode(statsReader);
    }

    @Override
    public final <W extends StatsWriter> W encode(T t, W writer) {
        return this.encode.encode(t, writer);
    }

    public final Decoder<T> decodeFunction() {
        return this.decode;
    }

    public final Encoder<T> encodeFunction() {
        return this.encode;
    }
}
