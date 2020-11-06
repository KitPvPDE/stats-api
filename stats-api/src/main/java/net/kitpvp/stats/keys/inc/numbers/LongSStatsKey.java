package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.api.functions.BiLongToLongFunction;
import net.kitpvp.stats.keys.inc.IncSStatsKey;

import java.util.function.BiFunction;

public interface LongSStatsKey extends LongStatsKey<Void>, IncSStatsKey<Long> {

    @Override
    BiFunction<Long, Long, Long> function();

    @Override
    BiLongToLongFunction longFunction();

    @Override
    Long neutral();

    @Override
    long neutralLong();

    @Override
    Long def();

    @Override
    long defLong();

    @Override
    Long apply(Long integer);

    @Override
    long applyLong(long l);

    @Override
    String key(Void unused);
}
