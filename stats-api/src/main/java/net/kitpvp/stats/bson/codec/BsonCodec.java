package net.kitpvp.stats.bson.codec;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.converter.Codec;

public interface BsonCodec<T> extends Codec<T, BsonStatsWriter>, BsonEncoder<T>, BsonDecoder<T> {

    @Override
    T decode(StatsReader statsReader);

    @Override
    BsonStatsWriter encode(T t, BsonStatsWriter statsWriter);
}
