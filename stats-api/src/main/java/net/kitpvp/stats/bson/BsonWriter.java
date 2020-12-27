package net.kitpvp.stats.bson;

import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.api.keys.VoidKey;
import net.kitpvp.stats.bson.codec.BsonEncoder;
import org.bson.Document;

public interface BsonWriter extends StatsWriter {

    Document bson();

    @Override
    default <T> void write(String key, T value) {
        BsonUtils.setValue(key, this.bson(), value);
    }

    default <K, U> void setBson(Key<K> statKey, K key, U value, BsonEncoder<U> encoder) {
        this.write(statKey.key(key), encoder.encode(value).bson());
    }

    default <U> void setBson(VoidKey statKey, U value, BsonEncoder<U> encoder) {
        this.setBson(statKey, null, value, encoder);
    }
}
