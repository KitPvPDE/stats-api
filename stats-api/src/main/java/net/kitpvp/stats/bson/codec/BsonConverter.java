package net.kitpvp.stats.bson.codec;

import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.converter.Codec;
import net.kitpvp.stats.converter.Converter;
import net.kitpvp.stats.converter.Decoder;
import net.kitpvp.stats.converter.Encoder;

public class BsonConverter<T> extends Converter<T, BsonStatsWriter> {

    public BsonConverter(Decoder<T> decode, Encoder<T, BsonStatsWriter> encode) {
        super(decode, encode);
    }

    public BsonConverter(Codec<T, BsonStatsWriter> context) {
        super(context);
    }

    public final StatsWriter encode(T t) {
        return this.encode.encode(t, new BsonStatsWriter());
    }
}
