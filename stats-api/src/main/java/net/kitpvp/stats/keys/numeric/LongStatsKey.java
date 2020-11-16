package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.api.numbers.NumberConversions;
import net.kitpvp.stats.builder.numeric.LongKeyBuilder;
import net.kitpvp.stats.keys.IncStatsKey;
import net.kitpvp.stats.reader.Reader;

import java.util.function.BinaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.util.function.UnaryOperator;

public interface LongStatsKey<K> extends IncStatsKey<K, Long> {

    static <K> LongKeyBuilder<K> builder() {
        return new LongKeyBuilder<>();
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

    @Override
    default Long extract(Reader statsReader, K key) {
        return this.extractLong(statsReader, key);
    }

    default long extractLong(Reader statsReader, K key) {
        return NumberConversions.getLong(this, key, statsReader);
    }
}
