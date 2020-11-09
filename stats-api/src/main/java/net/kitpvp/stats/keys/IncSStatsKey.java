package net.kitpvp.stats.keys;

import java.util.function.BinaryOperator;

public interface IncSStatsKey<V> extends IncStatsKey<Void, V>, SStatsKey<V> {

    @Override
    BinaryOperator<V> function();

    @Override
    V neutral();

    @Override
    V def();

    @Override
    String key(Void unused);

    @Override
    V apply(V v);
}
