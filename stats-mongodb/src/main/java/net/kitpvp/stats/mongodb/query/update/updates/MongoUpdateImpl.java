package net.kitpvp.stats.mongodb.query.update.updates;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.update.MongoUpdate;
import net.kitpvp.stats.query.update.Action;
import org.bson.Document;

@RequiredArgsConstructor
class MongoUpdateImpl<K, V> implements MongoUpdate {

    private final AppendableKey<K, V> statsKey;
    private final K k;
    private final V v;
    private final Action operator;

    @Override
    public MongoUpdate append(MongoStatsReader statsReader) {
        this.statsKey.append(this.k, this.v, (statsKey, key, value) -> {
            Document document = this.document(statsReader.source(), this.operator);
            String builtKey = statsKey.key(key);
            if(document.containsKey(builtKey)) {
                throw new IllegalArgumentException(String.format("Cannot update key %s (%s), key already set", statsKey, builtKey));
            } else {
                document.put(builtKey, value);
            }
        });
        return this;
    }
}
