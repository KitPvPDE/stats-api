package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.keys.AppendableIncKey;
import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public interface IncStatsKey<K, V> extends StatsKey<K, V>, AppendableIncKey<K, V> {

    BinaryOperator<V> function();

    V neutral();

    @Override
    V def();

    @Override
    String key(K k);

    @Override
    V apply(V v);
}
