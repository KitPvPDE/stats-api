package net.kitpvp.stats.keys;

import net.kitpvp.stats.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

abstract class SeasonKeyImpl<K, V, S extends StatsKey<K, V>> implements SeasonKey<K, V> {

    protected final KeyFunction<K> keyFunction;
    protected final BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping;
    protected final Supplier<V> defaultFunction;
    protected final Map<Integer, S> keys;

    SeasonKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping) {
        this.keyFunction = keyFunction;
        this.defaultFunction = defaultFunction;
        this.seasonKeyMapping = seasonKeyMapping;
        this.keys = new HashMap<>();
    }

    SeasonKeyImpl(@NotNull KeyFunction<K> keyFunction, BiFunction<KeyFunction<K>, Integer, KeyFunction<K>> seasonKeyMapping) {
        this(null, keyFunction, seasonKeyMapping);
    }

    @Override
    public abstract VoidSeasonKey<V> bind(K k);

    protected abstract S createKey(KeyFunction<K> function);

    @Override
    public S season(int season) {
        S key = this.keys.get(season);
        if(key == null) {
            key = this.createKey(seasonKeyMapping.apply(keyFunction, season));
            this.keys.put(season, key);
        }
        return key;
    }

    @Override
    public S season() {
        return this.season(Season.getSeason());
    }

    @Override
    public S alltime() {
        return this.season(Season.ALLTIME);
    }

    public KeyFunction<K> keyFunction() {
        return keyFunction;
    }

}
