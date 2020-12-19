package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.api.numbers.NumberConversions;
import net.kitpvp.stats.builder.numeric.IntKeyBuilder;
import net.kitpvp.stats.keys.IncStatsKey;
import net.kitpvp.stats.reader.Reader;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public interface IntStatsKey<K> extends IncStatsKey<K, Integer> {

    static <K> IntKeyBuilder<K> builder() {
        return new IntKeyBuilder<>();
    }

    Key<Integer> INT_KEY = Key.<Integer>builder().function(Objects::toString).inverse(Integer::parseInt).buildKey();

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
    Integer offset();

    int offsetInt();

    @Override
    Integer apply(Integer integer);

    int applyInt(int i);

    @Override
    String key(K k);

    @Override
    default Integer extract(Reader statsReader, K key) {
        return this.extractInt(statsReader, key);
    }

    default int extractInt(Reader statsReader, K key) {
        return NumberConversions.getInt(this, key, statsReader);
    }
}
