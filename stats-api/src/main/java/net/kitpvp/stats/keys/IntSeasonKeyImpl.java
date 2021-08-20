package net.kitpvp.stats.keys;

import java.util.function.BiFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.stream.Stream;

class IntSeasonKeyImpl<K> extends SeasonKeyImpl<K, Integer, IntStatsKey<K>> implements IntSeasonKey<K> {

    private final IntBinaryOperator sumFunction;
    private final IntUnaryOperator inverse;
    private final int neutral, def, offset;

    IntSeasonKeyImpl(KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, IntBinaryOperator sumFunction, IntUnaryOperator inverse, int neutral, int def, int offset) {
        super(keyFunction, seasonKeyMapping);
        this.sumFunction = sumFunction;
        this.inverse = inverse;
        this.neutral = neutral;
        this.def = def;
        this.offset = offset;
    }

    @Override
    public Stream<? extends IntStatsKey<K>> stream() {
        return Stream.of(this.alltime(), this.season());
    }

    @Override
    public IntVoidSeasonKey bind(K k) {
        return new IntVoidSeasonKeyImpl(KeyFunctions.bind(this.keyFunction, k), this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }

    @Override
    public IntStatsKey<K> season(int season) {
        return super.season(season);
    }

    @Override
    protected IntStatsKey<K> createKey(KeyFunction<K> function) {
        return new IntStatsKeyImpl<>(function, this.sumFunction, this.inverse, this.neutral, this.def, this.offset);
    }
}
