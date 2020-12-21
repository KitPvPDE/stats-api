package net.kitpvp.stats.converter;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;

@RequiredArgsConstructor
public abstract class Converter<T, Writer extends StatsWriter> implements Codec<T, Writer> {

    protected final Decoder<T> decode;
    protected final Encoder<T, Writer> encode;

    public Converter(Codec<T, Writer> context) {
        this(context, context);
    }

    public final T decode(StatsReader statsReader) {
        return this.decode.decode(statsReader);
    }

    public final Writer encode(T t, Writer writer) {
        return this.encode.encode(t, writer);
    }

    public final Decoder<T> decodeFunction() {
        return this.decode;
    }

    public final Encoder<T, Writer> encodeFunction() {
        return this.encode;
    }
}
