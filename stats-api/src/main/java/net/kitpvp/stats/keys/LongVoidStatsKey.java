package net.kitpvp.stats.keys;

import net.kitpvp.stats.VoidKey;

import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.stream.Stream;

public interface LongVoidStatsKey extends LongStatsKey<Void>, NumericVoidStatsKey<Long>, VoidStatsKey<Long> {

    static LongVoidKeyBuilder builder() {
        return new LongVoidKeyBuilder();
    }

    @Override
    BinaryOperator<Long> addition();

    @Override
    LongBinaryOperator additionLong();

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

    @Override
    default LongVoidStatsKey bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends LongVoidStatsKey> stream() {
        return Stream.of(this);
    }
}
