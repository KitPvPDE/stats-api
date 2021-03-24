package net.kitpvp.stats.bson.codec;

import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.bson.BsonWriter;
import net.kitpvp.stats.converter.Encoder;

@FunctionalInterface
public interface BsonEncoder<T> extends Encoder<T, BsonStatsWriter> {

    default BsonStatsWriter encode(T t) {
        BsonStatsWriter writer = new BsonStatsWriter();
        this.encode(t, writer);
        return writer;
    }
}
