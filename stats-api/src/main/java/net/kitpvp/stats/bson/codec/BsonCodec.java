package net.kitpvp.stats.bson.codec;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.converter.Codec;

public interface BsonCodec<T> extends Codec<T>, BsonEncoder<T>, BsonDecoder<T> {

    @Override
    T decode(StatsReader statsReader);

    @Override
    <W extends StatsWriter> W encode(T t, W statsWriter);
}
