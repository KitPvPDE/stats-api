package net.kitpvp.api.stats.keys;

import org.bson.Document;

public interface StatsKey<K, V> {

    V getDefault();

    Document append(Document to, K k, V v);

    String getKey(K k);

    V apply(V v);


}
