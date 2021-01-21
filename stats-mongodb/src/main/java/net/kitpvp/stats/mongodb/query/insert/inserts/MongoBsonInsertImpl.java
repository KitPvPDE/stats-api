package net.kitpvp.stats.mongodb.query.insert.inserts;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.bson.codec.BsonEncoder;
import net.kitpvp.stats.mongodb.query.insert.MongoInsert;

@RequiredArgsConstructor
final class MongoBsonInsertImpl<K, U> implements MongoInsert {

    private final Key<K> statsKey;
    private final K key;
    private final U value;
    private final BsonEncoder<U> encoder;

    @Override
    public MongoInsert append(BsonStatsWriter writer) {
        if(this.statsKey != null) {
            writer.bson().append(this.statsKey.key(this.key), this.encoder.encode(this.value));
        } else {
            writer.bson().putAll(this.encoder.encode(this.value).bson());
        }
        return this;
    }
}
