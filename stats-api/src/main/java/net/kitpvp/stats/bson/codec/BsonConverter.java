package net.kitpvp.stats.bson.codec;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.bson.BsonStatsWriter;

public class BsonConverter<T> implements BsonCodec<T> {

    protected final BsonDecoder<T> decode;
    protected final BsonEncoder<T> encode;

    public BsonConverter(BsonDecoder<T> decode, BsonEncoder<T> encode) {
        this.decode = decode;
        this.encode = encode;
    }

    public BsonConverter(BsonCodec<T> context) {
        this.decode = context;
        this.encode = context;
    }

    @Override
    public T decode(StatsReader statsReader) {
        return this.decode.decode(statsReader);
    }

    @Override
    public void encode(T t, BsonStatsWriter statsWriter) {
        this.encode.encode(t, statsWriter);
    }

    public final BsonStatsWriter encode(T t) {
        return this.encode.encode(t);
    }

    public BsonEncoder<T> encodeFunction() {
        return this.encode;
    }

    public BsonDecoder<T> decodeFunction() {
        return this.decode;
    }
}
