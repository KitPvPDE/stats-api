package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.api.functions.BiIntToIntFunction;
import net.kitpvp.stats.keys.inc.IncStatsKey;

import java.util.function.BiFunction;

public interface IntStatsKey<K> extends IncStatsKey<K, Integer> {

    BiFunction<Integer, Integer, Integer> function();

    BiIntToIntFunction intFunction();

    @Override
    @Deprecated
    Integer neutral();

    int neutralInt();

    @Override
    @Deprecated
    Integer def();

    int defInt();

    @Override
    Integer apply(Integer integer);

    int applyInt(int i);

    @Override
    String key(K k);
}
