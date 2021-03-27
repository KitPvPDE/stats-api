package net.kitpvp.stats.converter;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public abstract class Converter<T, Writer extends StatsWriter> implements Codec<T, Writer> {

    protected final Decoder<T> decode;
    protected final Encoder<T, Writer> encode;

    public Converter(Codec<T, Writer> context) {
        this(context, context);
    }

    @Override
    public final @NotNull T decode(StatsReader statsReader) {
        return this.decode.decode(statsReader);
    }

    @Override
    public final void encode(T t, Writer writer) {
        this.encode.encode(t, writer);
    }

    public Decoder<T> decodeFunction() {
        return this.decode;
    }

    public Encoder<T, Writer> encodeFunction() {
        return this.encode;
    }
}
