package net.kitpvp.stats.converter;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.bson.BsonStatsWriter;

@RequiredArgsConstructor
public class Converter<T> implements Context<T> {

    private final Decoder<T> decode;
    private final Encoder<T> encode;

    public Converter(Context<T> context) {
        this(context, context);
    }

    public final T decode(StatsReader statsReader) {
        return this.decode.decode(statsReader);
    }

    public final StatsWriter encode(T t) {
        return this.encode.encode(t, new BsonStatsWriter());
    }

    public final StatsWriter encode(T t, StatsWriter writer) {
        return this.encode.encode(t, writer);
    }

    public final Decoder<T> decodeFunction() {
        return this.decode;
    }

    public final Encoder<T> encodeFunction() {
        return this.encode;
    }
}
