package net.kitpvp.stats.keys;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public interface IntVoidStatsKey extends IntStatsKey<Void>, NumericVoidStatsKey<Integer>,
        IterableIntVoidKey {

    static IntVoidKeyBuilder builder() {
        return new IntVoidKeyBuilder();
    }

    @Override
    BinaryOperator<Integer> addition();

    @Override
    IntBinaryOperator additionInt();

    @Override
    UnaryOperator<Integer> inverse();

    IntUnaryOperator inverseInt();

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

    @Override
    default IntVoidStatsKey bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends IntVoidStatsKey> stream() {
        return Stream.of(this);
    }
}
