package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.numbers.NumberConversions;
import net.kitpvp.stats.reader.Reader;

import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.UnaryOperator;

public interface LongStatsKey<K> extends NumericStatsKey<K, Long> {

    static <K> LongKeyBuilder<K> builder() {
        return new LongKeyBuilder<>();
    }

    static LongStatsKey<String> identity() {
        return LongStatsKeyImpl.IDENTITY;
    }

    BinaryOperator<Long> addition();

    LongBinaryOperator additionLong();

    @Override
    UnaryOperator<Long> inverse();

    LongUnaryOperator inverseLong();

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
    Long offset();

    long offsetLong();

    @Override
    String key(K k);

    LongVoidStatsKey bind(K k);

    @Override
    default Long extract(Reader statsReader, K key) {
        return this.extractLong(statsReader, key);
    }

    default long extractLong(Reader statsReader, K key) {
        return NumberConversions.getLong(this, key, statsReader);
    }
}
