package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.api.functions.BiDoubleToDoubleFunction;
import net.kitpvp.stats.keys.inc.IncStatsKey;

import java.util.function.BiFunction;

public interface DoubleStatsKey<K> extends IncStatsKey<K, Double> {

    BiFunction<Double, Double, Double> function();

    BiDoubleToDoubleFunction doubleFunction();

    @Override
    @Deprecated
    Double neutral();

    double neutralDouble();

    @Override
    @Deprecated
    Double def();

    double defDouble();

    @Override
    Double apply(Double integer);

    double applyDouble(double d);

    @Override
    String key(K k);
}
