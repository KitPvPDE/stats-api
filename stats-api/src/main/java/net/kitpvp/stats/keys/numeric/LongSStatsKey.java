package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.builder.numeric.LongVoidKeyBuilder;
import net.kitpvp.stats.keys.IncSStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;

public interface LongSStatsKey extends LongStatsKey<Void>, IncSStatsKey<Long> {

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
}
