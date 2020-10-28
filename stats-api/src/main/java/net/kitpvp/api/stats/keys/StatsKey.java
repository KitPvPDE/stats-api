package net.kitpvp.api.stats.keys;

import net.kitpvp.api.stats.keys.builder.Builder;
import org.bson.Document;

public interface StatsKey<K, V> {

    static Builder builder() {
        return new Builder();
    }

    V getDefault();

    Document append(Document to, K k, V v);

    String getKey(K k);

    V apply(V v);
}
