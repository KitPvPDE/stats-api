package net.kitpvp.stats.keys.inc;

import net.kitpvp.stats.api.keys.AppendableIncKey;
import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;

import java.util.function.BiFunction;

public interface IncStatsKey<K, V> extends StatsKey<K, V>, AppendableIncKey<K, V> {

    BiFunction<V, V, V> function();

    V neutral();

    @Override
    V def();

    @Override
    String key(K k);

    @Override
    V apply(V v);
}
