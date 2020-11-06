package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.api.functions.BiLongToLongFunction;
import net.kitpvp.stats.keys.inc.IncStatsKey;

import java.util.function.BiFunction;

public interface LongStatsKey<K> extends IncStatsKey<K, Long> {

    BiFunction<Long, Long, Long> function();

    BiLongToLongFunction longFunction();

    @Override
    @Deprecated
    Long neutral();

    long neutralLong();

    @Override
    @Deprecated
    Long def();

    long defLong();

    @Override
    Long apply(Long integer);

    long applyLong(long l);

    @Override
    String key(K k);
}
