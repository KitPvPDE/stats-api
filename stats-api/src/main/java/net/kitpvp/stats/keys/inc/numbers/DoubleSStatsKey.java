package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.api.functions.BiDoubleToDoubleFunction;
import net.kitpvp.stats.keys.inc.IncSStatsKey;

import java.util.function.BiFunction;

public interface DoubleSStatsKey extends DoubleStatsKey<Void>, IncSStatsKey<Double> {

    @Override
    BiFunction<Double, Double, Double> function();

    @Override
    BiDoubleToDoubleFunction doubleFunction();

    @Override
    Double neutral();

    @Override
    double neutralDouble();

    @Override
    Double def();

    @Override
    double defDouble();

    @Override
    Double apply(Double integer);

    @Override
    double applyDouble(double d);

    @Override
    String key(Void unused);
}
