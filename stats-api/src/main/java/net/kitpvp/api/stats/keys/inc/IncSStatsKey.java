package net.kitpvp.api.stats.keys.inc;

import org.bson.Document;

import java.util.function.BiFunction;

public interface IncSStatsKey<V> extends IncStatsKey<Void, V> {

    @Override
    BiFunction<V, V, V> function();

    @Override
    V neutral();

    @Override
    V getDefault();

    @Override
    Document append(Document to, Void unused, V v);

    @Override
    String getKey(Void unused);

    @Override
    V apply(V v);
}
