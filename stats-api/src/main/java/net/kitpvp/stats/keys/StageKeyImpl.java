package net.kitpvp.stats.keys;

import net.kitpvp.stats.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

abstract class StageKeyImpl<K, V, S extends StatsKey<K, V>> extends SeasonKeyImpl<K, V, S> implements StageKey<K, V> {

    protected final UnaryOperator<KeyFunction<K>> remapFunction;
    private S key;

    StageKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, UnaryOperator<KeyFunction<K>> remapFunction) {
        super(defaultFunction, keyFunction, seasonKeyMapping);
        this.remapFunction = remapFunction;
    }

    StageKeyImpl(@NotNull KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping, UnaryOperator<KeyFunction<K>> remapFunction) {
        super(keyFunction, seasonKeyMapping);
        this.remapFunction = remapFunction;
    }

    @Override
    public S stage() {
        if(this.key == null) {
            this.key = this.createKey(remapFunction.apply(this.keyFunction));
        }
        return key;
    }

    protected abstract S createKey(KeyFunction<K> function);
}
