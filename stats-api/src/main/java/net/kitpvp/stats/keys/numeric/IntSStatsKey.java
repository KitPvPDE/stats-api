package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.builder.numeric.IntVoidKeyBuilder;
import net.kitpvp.stats.keys.IncSStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public interface IntSStatsKey extends IntStatsKey<Void>, IncSStatsKey<Integer> {

    static IntVoidKeyBuilder builder() {
        return new IntVoidKeyBuilder();
    }

    @Override
    BinaryOperator<Integer> function();

    @Override
    IntBinaryOperator intFunction();

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
