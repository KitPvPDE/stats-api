package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.api.functions.BiIntToIntFunction;
import net.kitpvp.stats.keys.inc.IncSStatsKey;

import java.util.function.BiFunction;

public interface IntSStatsKey extends IntStatsKey<Void>, IncSStatsKey<Integer> {

    @Override
    BiFunction<Integer, Integer, Integer> function();

    @Override
    BiIntToIntFunction intFunction();

    @Override
    Integer neutral();

    @Override
    int neutralInt();

    @Override
    Integer def();

    @Override
    int defInt();

    @Override
    Integer apply(Integer integer);

    @Override
    int applyInt(int i);

    @Override
    String key(Void unused);
}
