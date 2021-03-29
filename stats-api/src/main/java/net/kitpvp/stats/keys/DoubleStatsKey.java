package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.numbers.NumberConversions;
import net.kitpvp.stats.reader.Reader;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public interface DoubleStatsKey<K> extends NumericStatsKey<K, Double>, IterableDoubleKey<K> {

    static <K> DoubleKeyBuilder<K> builder() {
        return new DoubleKeyBuilder<>();
    }

    static DoubleStatsKey<String> identity() {
        return DoubleStatsKeyImpl.IDENTITY;
    }

    BinaryOperator<Double> addition();

    DoubleBinaryOperator additionDouble();

    UnaryOperator<Double> inverse();

    DoubleUnaryOperator inverseDouble();

    @Override
    @Deprecated
    Double neutral();

    double neutralDouble();

    @Override
    @Deprecated
    Double def();

    double defDouble();

    @Override
    Double offset();

    double offsetDouble();

    @Override
    Double apply(Double integer);

    double applyDouble(double d);

    @Override
    String key(K k);

    @Override
    default Stream<? extends DoubleStatsKey<K>> stream() {
        return Stream.of(this);
    }

    @Override
    default Double extract(Reader statsReader, K key) {
        return this.extractDouble(statsReader, key);
    }

    default double extractDouble(Reader statsReader, K key) {
        return NumberConversions.getDouble(this, key, statsReader);
    }
}
