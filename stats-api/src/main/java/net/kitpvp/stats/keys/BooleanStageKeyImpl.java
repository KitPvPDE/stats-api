package net.kitpvp.stats.keys;

import java.util.function.BiFunction;
import java.util.function.UnaryOperator;

class BooleanStageKeyImpl<K> extends StageKeyImpl<K, Boolean, BooleanStatsKey<K>> implements BooleanStageKey<K> {

    private final boolean def;

    BooleanStageKeyImpl(KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, UnaryOperator<KeyFunction<K>> remapFunction, boolean def) {
        super(keyFunction, seasonKeyMapping, remapFunction);
        this.def = def;
    }

    @Override
    public BooleanVoidStageKey bind(K k) {
        return new BooleanVoidStageKeyImpl(this.keyFunction.bind(k),
                (function) -> this.remapFunction.apply(this.keyFunction).bind(k), this.def);
    }

    @Override
    protected BooleanStatsKey<K> createKey(KeyFunction<K> function) {
        return new BooleanStatsKeyImpl<>(function, this.def);
    }
}
