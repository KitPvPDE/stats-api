package net.kitpvp.stats.mongodb.query.update.updates;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import net.kitpvp.mongodbapi.log.Log;
import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.update.MongoUpdate;
import net.kitpvp.stats.query.update.Action;
import org.bson.Document;

import java.util.concurrent.atomic.AtomicInteger;

class MongoUpdateImpl<K, V> implements MongoUpdate {

    private final AppendableKey<K, V> statsKey;
    private final K k;
    private final V v;
    private final Action operator;

    public MongoUpdateImpl(AppendableKey<K, V> statsKey, K k, V v, Action operator) {
        Preconditions.checkNotNull(statsKey, "statsKey");
        this.statsKey = statsKey;
        this.k = k;
        this.v = v;
        this.operator = operator;
    }

    @Override
    public MongoUpdate append(MongoStatsReader statsReader) {
        this.statsKey.append(this.k, this.v, (statsKey, key, value) -> {
            Document document = this.document(statsReader.source(), this.operator);
            String builtKey = statsKey.key(key);
            if(document.containsKey(builtKey)) {
                Log.debug(String.format("Updating key %s, key was already set to %s (%s)", builtKey, document.get(builtKey), document));
            }
            document.put(builtKey, value);
        });
        return this;
    }
}
