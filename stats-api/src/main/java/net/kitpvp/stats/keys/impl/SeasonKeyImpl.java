package net.kitpvp.stats.keys.impl;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.SeasonKey;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class SeasonKeyImpl<K, V, S extends StatsKey<K, V>> implements SeasonKey<K, V> {

    protected final BiFunction<Supplier<V>, Function<K, String>, S> keyConstructor;
    protected final Function<K, String> keyFunction;
    protected final Supplier<V> defaultFunction;
    private final List<S> keys;

    public SeasonKeyImpl(@Nullable BiFunction<Supplier<V>, Function<K, String>, S> keyConstructor, @Nullable Supplier<V> defaultFunction, @NotNull Function<K, String> keyFunction) {
        this.keyConstructor = keyConstructor;
        this.keyFunction = keyFunction;
        this.defaultFunction = defaultFunction;
        this.keys = new ArrayList<>();
        for(int i = 0; i <= Season.getSeason(); i++) {
            this.keys.add(null);
        }
    }

    public SeasonKeyImpl(@NotNull Function<K, String> keyFunction) {
        this(null, null, keyFunction);
    }

    @Override
    public S season(int season) {
        this.checkCapacity(season);
        S key = this.keys.get(season);
        if(key == null) {
            key = this.createKey(season);
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

    protected S createKey(int season) {
        return this.keyConstructor.apply(this.defaultFunction, this.createKeyFunction(season));
    }

    protected final Function<K, String> createKeyFunction(int season) {
        return new SeasonToKeyFunction(this, season);
    }

    private void checkCapacity(int season) {
        while(this.keys.size() <= season) {
            this.keys.add(null);
        }
    }

    @RequiredArgsConstructor
    protected class SeasonToKeyFunction implements Function<K, String> {

        protected final SeasonKeyImpl<K, V, S> seasonKey;
        protected final int season;

        @Override
        public String apply(K k) {
            return (this.season == 0 ? "alltime" : "seasons.season" + this.season) + "." + this.seasonKey.keyFunction.apply(k);
        }
    }

}
