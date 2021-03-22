package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.functions.season.SeasonKeyFunction;
import net.kitpvp.stats.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

abstract class SeasonKeyImpl<K, V, S extends StatsKey<K, V>> implements SeasonKey<K, V> {

    protected final KeyFunction<K> keyFunction;
    protected final Supplier<V> defaultFunction;
    private final List<S> keys;

    SeasonKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull KeyFunction<K> keyFunction) {
        this.keyFunction = keyFunction;
        this.defaultFunction = defaultFunction;
        this.keys = new ArrayList<>();
        for(int i = 0; i <= Season.getSeason(); i++) {
            this.keys.add(null);
        }
    }

    SeasonKeyImpl(@NotNull KeyFunction<K> keyFunction) {
        this(null, keyFunction);
    }

    @Override
    public abstract VoidSeasonKey<V> bind(K k);

    protected abstract S createKey(KeyFunction<K> function);

    @Override
    public S season(int season) {
        this.checkCapacity(season);
        S key = this.keys.get(season);
        if(key == null) {
            key = this.createKey(this.createKeyFunction(season));
            this.keys.set(season, key);
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

    protected final KeyFunction<K> createKeyFunction(int season) {
        return new SeasonKeyFunction<>(this.keyFunction, season);
    }

    private void checkCapacity(int season) {
        while(this.keys.size() <= season) {
            this.keys.add(null);
        }
    }

}
