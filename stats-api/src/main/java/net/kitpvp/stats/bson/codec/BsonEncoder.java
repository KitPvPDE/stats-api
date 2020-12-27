package net.kitpvp.stats.bson.codec;

import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.converter.Encoder;

@FunctionalInterface
public interface BsonEncoder<T> extends Encoder<T, BsonStatsWriter> {

    @Override
    BsonStatsWriter encode(T t, BsonStatsWriter statsWriter);

    default BsonStatsWriter encode(T t) {
        return this.encode(t, new BsonStatsWriter());
    }
}
