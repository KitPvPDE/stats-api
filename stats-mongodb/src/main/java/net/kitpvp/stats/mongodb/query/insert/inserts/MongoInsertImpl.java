package net.kitpvp.stats.mongodb.query.insert.inserts;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.mongodb.query.insert.MongoInsert;

@RequiredArgsConstructor
final class MongoInsertImpl<K, V> implements MongoInsert {

    private final StatsKey<K, V> statsKey;
    private final K key;
    private final V value;

    @Override
    public MongoInsert append(BsonStatsWriter statsReader) {
        this.statsKey.append(this.key, this.value, (statKey, key, value) ->
                statsReader.bson().append(statKey.key(key), value));
        return this;
    }
}
