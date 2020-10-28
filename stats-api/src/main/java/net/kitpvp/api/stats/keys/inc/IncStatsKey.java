package net.kitpvp.api.stats.keys.inc;

import net.kitpvp.api.stats.keys.StatsKey;
import org.bson.Document;

import java.util.function.BiFunction;

public interface IncStatsKey<K, V> extends StatsKey<K, V> {

    BiFunction<V, V, V> function();

    V neutral();

    @Override
    V getDefault();

    @Override
    Document append(Document to, K k, V v);

    @Override
    String getKey(K k);

    @Override
    V apply(V v);
}
