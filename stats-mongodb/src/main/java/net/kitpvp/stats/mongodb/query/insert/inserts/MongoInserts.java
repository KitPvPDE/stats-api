package net.kitpvp.stats.mongodb.query.insert.inserts;

import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.api.keys.VoidKey;
import net.kitpvp.stats.bson.codec.BsonEncoder;
import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.mongodb.query.insert.MongoInsert;

public interface MongoInserts {

    static <K, V> MongoInsert insert(StatsKey<K, V> statsKey, K key, V value) {
        return new MongoInsertImpl<>(statsKey, key, value);
    }

    static <V> MongoInsert insert(SStatsKey<V> statsKey, V value) {
        return insert(statsKey, null, value);
    }

    static <K, U> MongoInsert insertBson(Key<K> statsKey, K key, U value, BsonEncoder<U> encoder) {
        return new MongoBsonInsertImpl<>(statsKey, key, value, encoder);
    }

    static <U> MongoInsert insertBson(VoidKey statsKey, U value, BsonEncoder<U> encoder) {
        return insertBson(statsKey, null, value, encoder);
    }

    static <U> MongoInsert insertBson(U value, BsonEncoder<U> encoder) {
        return new MongoBsonInsertImpl<>(null, null, value, encoder);
    }
}
