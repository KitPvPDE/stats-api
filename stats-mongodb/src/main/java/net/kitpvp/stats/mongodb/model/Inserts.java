package net.kitpvp.stats.mongodb.model;

import net.kitpvp.stats.Key;
import net.kitpvp.stats.VoidKey;
import net.kitpvp.stats.bson.codec.BsonEncoder;
import org.bson.Document;

import static com.mongodb.assertions.Assertions.notNull;

public final class Inserts {

    public static <V> Document insert(VoidKey statKey, V value) {
        return new Document(statKey.key(), value);
    }

    public static <K, V> Document insert(Key<K> statKey, K key, V value) {
        return new Document(statKey.key(key), value);
    }

    public static <K, U> Document insert(Key<K> statsKey, K key, U value, BsonEncoder<U> encoder) {
        return new Document(statsKey.key(key), encoder.encode(value).bson());
    }

    public static <U> Document insert(VoidKey statsKey, U value, BsonEncoder<U> encoder) {
        return new Document(statsKey.key(), encoder.encode(value).bson());
    }

    public static <U> Document insert(U value, BsonEncoder<U> encoder) {
        return encoder.encode(value).bson();
    }

    private static <K> String buildKey(Key<K> statKey, K key) {
        return notNull("statKey", statKey).key(notNull("key", key));
    }
}
