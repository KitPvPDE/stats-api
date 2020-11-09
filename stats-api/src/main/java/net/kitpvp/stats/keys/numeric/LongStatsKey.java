package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.builder.numeric.LongKeyBuilder;
import net.kitpvp.stats.keys.IncStatsKey;

import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;

public interface LongStatsKey<K> extends IncStatsKey<K, Long> {

    static <K> LongKeyBuilder<K> builder() {
        return new LongKeyBuilder<>();
    }

    BinaryOperator<Long> function();

    LongBinaryOperator longFunction();

    @Override
    @Deprecated
    Long neutral();

    long neutralLong();

    @Override
    @Deprecated
    Long def();

    long defLong();

    @Override
    Long apply(Long integer);

    long applyLong(long l);

    @Override
    String key(K k);
}
