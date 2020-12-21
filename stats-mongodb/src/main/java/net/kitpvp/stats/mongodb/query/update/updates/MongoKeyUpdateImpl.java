package net.kitpvp.stats.mongodb.query.update.updates;

import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.bson.codec.BsonEncoder;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.update.MongoUpdate;
import net.kitpvp.stats.query.update.Action;
import org.bson.Document;

public class MongoKeyUpdateImpl<K, U> implements MongoUpdate {

    private final Key<K> statsKey;
    private final K key;
    private final BsonEncoder<U> encoder;
    private final U value;
    private final Action operator;

    public MongoKeyUpdateImpl(Key<K> statsKey, K key, BsonEncoder<U> encoder, U value, Action action) {
        this.statsKey = statsKey;
        this.key = key;
        this.encoder = encoder;
        this.value = value;
        this.operator = action;
    }

    @Override
    public MongoUpdate append(MongoStatsReader statsReader) {
        Document document = this.document(statsReader.source(), this.operator);
        String builtKey = statsKey.key(this.key);
        if(document.containsKey(builtKey)) {
            Log.debug(String.format("Updating key %s, key was already set to %s (%s)", builtKey, document.get(builtKey), document));
        }
        document.put(builtKey, this.encoder.encode(this.value, new BsonStatsWriter()).bson());
        return this;
    }
}
