package net.kitpvp.stats.keys.impl;

import com.google.common.base.Preconditions;
import net.kitpvp.stats.api.functions.keys.KeyFunction;
import net.kitpvp.stats.keys.SStageKey;
import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class VoidStageKeyImpl<V, S extends SStatsKey<V>> extends VoidSeasonKeyImpl<V, S> implements SStageKey<V> {

    private final Map<Integer, List<S>> seasonKeys;

    public VoidStageKeyImpl(@Nullable BiFunction<Supplier<V>, KeyFunction<Void>, S> keyConstructor, @Nullable Supplier<V> defaultFunction, @NotNull KeyFunction<Void> keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
        this.seasonKeys = new HashMap<>();
    }

    public VoidStageKeyImpl(@NotNull KeyFunction<Void> keyFunction) {
        super(keyFunction);
        this.seasonKeys = new HashMap<>();
    }

    @Override
    public S stage(int season, int stage) {
        this.checkCapacity(season, stage);
        S key = this.seasonKeys.get(season).get(stage);
        if(key == null) {
            key = this.createKey(season, stage);
            this.seasonKeys.get(season).set(stage, key);
        }
        return key;
    }

    @Override
    public S stage() {
        return this.stage(Season.getSeason(), Season.getStage());
    }

    protected S createKey(int season, int stage) {
        return this.keyConstructor.apply(this.defaultFunction, this.createKeyFunction(season));
    }

    protected final KeyFunction<Void> createKeyFunction(int season, int stage) {
        return new SeasonToKeyFunction(this, season, stage);
    }

    private void checkCapacity(int season, int stage) {
        List<S> keys = this.seasonKeys.computeIfAbsent(season, (x) -> new ArrayList<>());
        while(keys.size() <= stage) {
            keys.add(null);
        }
    }

    protected class SeasonToKeyFunction implements KeyFunction<Void> {

        protected final VoidSeasonKeyImpl<V, S> seasonKey;
        protected final int season, stage;

        public SeasonToKeyFunction(VoidSeasonKeyImpl<V, S> seasonKey, int season, int stage) {
            this.seasonKey = seasonKey;
            this.season = season;
            this.stage = stage;

            Preconditions.checkArgument(this.season > 0, "Season must be > 0");
        }

        @Override
        public String apply(Void k) {
            return ("seasons.season" + this.season + ".stages.stage" + this.stage) + "." + this.seasonKey.keyFunction.apply(k);
        }

        @Override
        public String prefix() {
            return ("seasons.season" + this.season + ".stages.stage" + this.stage) + "." + this.seasonKey.keyFunction.prefix();
        }

        @Override
        public String suffix() {
            return this.seasonKey.keyFunction.suffix();
        }
    }
}
