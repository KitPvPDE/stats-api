package net.kitpvp.stats.bson.codec;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.converter.Codec;
import org.jetbrains.annotations.NotNull;

public interface BsonCodec<T> extends Codec<T, BsonStatsWriter>, BsonEncoder<T>, BsonDecoder<T> {

    @Override
    @NotNull
    T decode(StatsReader statsReader);

    @Override
    void encode(T t, BsonStatsWriter statsWriter);
}
