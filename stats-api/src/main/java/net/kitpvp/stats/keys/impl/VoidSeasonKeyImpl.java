package net.kitpvp.stats.keys.impl;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.SSeasonKey;
import net.kitpvp.stats.keys.SStatsKey;
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

public class VoidSeasonKeyImpl<V, S extends SStatsKey<V>> implements SSeasonKey<V> {

    protected final BiFunction<Supplier<V>, KeyFunction<Void>, S> keyConstructor;
    protected final KeyFunction<Void> keyFunction;
    protected final Supplier<V> defaultFunction;
    private final List<S> keys;

    public VoidSeasonKeyImpl(@Nullable BiFunction<Supplier<V>, KeyFunction<Void>, S> keyConstructor, @Nullable Supplier<V> defaultFunction, @NotNull KeyFunction<Void> keyFunction) {
        this.keyConstructor = keyConstructor;
        this.keyFunction = keyFunction;
        this.defaultFunction = defaultFunction;
        this.keys = new ArrayList<>();
        for(int i = 0; i <= Season.getSeason(); i++) {
            this.keys.add(null);
        }
    }

    public VoidSeasonKeyImpl(@NotNull KeyFunction<Void> keyFunction) {
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

    protected final KeyFunction<Void> createKeyFunction(int season) {
        return new SeasonToKeyFunction(this, season);
    }

    private void checkCapacity(int season) {
        while(this.keys.size() <= season) {
            this.keys.add(null);
        }
    }

    @RequiredArgsConstructor
    protected class SeasonToKeyFunction implements KeyFunction<Void> {

        protected final VoidSeasonKeyImpl<V, S> seasonKey;
        protected final int season;

        @Override
        public String apply(Void k) {
            return (this.season == 0 ? "alltime" : "seasons.season" + this.season) + "." + this.seasonKey.keyFunction.apply(k);
        }

        @Override
        public String prefix() {
            return (this.season == 0 ? "alltime" : "seasons.season" + this.season) + "." + this.seasonKey.keyFunction.prefix();
        }

        @Override
        public String suffix() {
            return this.seasonKey.keyFunction.suffix();
        }
    }

}
