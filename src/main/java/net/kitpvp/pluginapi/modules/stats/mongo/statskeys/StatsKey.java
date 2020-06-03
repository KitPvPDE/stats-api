package net.kitpvp.pluginapi.modules.stats.mongo.statskeys;

import org.bson.Document;

public interface StatsKey<K, V> {

    V getDefault();

    Document append(Document to, K k, V v);

    String getKey(K k);

    V apply(V v);

}
