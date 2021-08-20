package net.kitpvp.stats.keys;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.builder.StatsKeyBuilder;
import net.kitpvp.stats.api.functions.season.SeasonKeyFunction;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class NumericKeyBuilder<K, V> implements StatsKeyBuilder<K, V> {

    protected final KeyBuilder<K> keyBuilder = new KeyBuilder<>();
    private BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping = SeasonKeyFunction::new;
    private BinaryOperator<V> addition;
    private UnaryOperator<V> reverse;
    private V neutral, def, offset;

    public NumericKeyBuilder<K, V> def(V def) {
        this.def = def;
        return this;
    }

    public NumericKeyBuilder<K, V> offset(V offset) {
        this.offset = offset;
        return this;
    }

    public NumericKeyBuilder<K, V> neutral(V neutral) {
        this.neutral = neutral;
        return this;
    }

    public NumericKeyBuilder<K, V> addition(BinaryOperator<V> addition) {
        this.addition = addition;
        return this;
    }

    public NumericKeyBuilder<K, V> inverse(UnaryOperator<V> inverse) {
        this.reverse = inverse;
        return this;
    }

    public NumericKeyBuilder<K, V> seasonKeyMapping(BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping) {
        this.seasonKeyMapping = seasonKeyMapping;
        return this;
    }

    @Override
    public @NotNull NumericStatsKey<K, V> build() {
        return new NumericStatsKeyImpl<>(this.keyBuilder.build(), this.addition, this.reverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull NumericSeasonKey<K, V> season() {
        return new NumericSeasonKeyImpl<>(this.keyBuilder.build(), seasonKeyMapping, this.addition, this.reverse, this.neutral, this.def, this.offset);
    }

    @Override
    public @NotNull NumericStageKey<K, V> stage(UnaryOperator<KeyFunction<K>> remapFunction) {
        return new NumericStageKeyImpl<>(this.keyBuilder.build(), seasonKeyMapping, remapFunction, this.addition, this.reverse, this.neutral, this.def, this.offset);
    }
}
