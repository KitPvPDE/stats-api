package net.kitpvp.stats.keys.inc;

import net.kitpvp.stats.keys.SStatsKey;
import org.bson.Document;

import java.util.function.BiFunction;

public interface IncSStatsKey<V> extends IncStatsKey<Void, V>, SStatsKey<V> {

    @Override
    BiFunction<V, V, V> function();

    @Override
    V neutral();

    @Override
    V def();

    @Override
    String key(Void unused);

    @Override
    V apply(V v);
}
