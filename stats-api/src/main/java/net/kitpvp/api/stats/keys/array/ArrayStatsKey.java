package net.kitpvp.api.stats.keys.array;

import net.kitpvp.api.stats.keys.StatsKey;
import org.bson.Document;

import java.util.List;

public interface ArrayStatsKey<K, X, V extends List<X>> extends StatsKey<K, V> {

    @Override
    V getDefault();

    @Override
    Document append(Document to, K k, V vs);

    @Override
    String getKey(K k);

    @Override
    V apply(V vs);
}
