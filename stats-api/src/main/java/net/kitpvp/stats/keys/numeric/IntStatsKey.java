package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.builder.numeric.IntKeyBuilder;
import net.kitpvp.stats.keys.IncStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public interface IntStatsKey<K> extends IncStatsKey<K, Integer> {

    static <K> IntKeyBuilder<K> builder() {
        return new IntKeyBuilder<>();
    }

    BinaryOperator<Integer> addition();

    IntBinaryOperator additionInt();

    @Override
    UnaryOperator<Integer> inverse();

    IntUnaryOperator inverseInt();

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
