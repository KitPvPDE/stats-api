package net.kitpvp.stats.bson.codec;

import net.kitpvp.stats.converter.Decoder;

@FunctionalInterface
public interface BsonDecoder<T> extends Decoder<T> {
}
