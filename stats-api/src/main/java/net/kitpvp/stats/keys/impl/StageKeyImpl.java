package net.kitpvp.stats.keys.impl;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.StageKey;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class StageKeyImpl<K, V, S extends StatsKey<K, V>> extends SeasonKeyImpl<K, V, S> implements StageKey<K, V> {

    private final Map<Integer, List<S>> seasonKeys;

    public StageKeyImpl(@Nullable BiFunction<Supplier<V>, Function<K, String>, S> keyConstructor, @Nullable Supplier<V> defaultFunction, @NotNull Function<K, String> keyFunction) {
        super(keyConstructor, defaultFunction, keyFunction);
        this.seasonKeys = new HashMap<>();
    }

    public StageKeyImpl(@NotNull Function<K, String> keyFunction) {
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

    protected final Function<K, String> createKeyFunction(int season, int stage) {
        return new SeasonToKeyFunction(this, season, stage);
    }

    private void checkCapacity(int season, int stage) {
        List<S> keys = this.seasonKeys.computeIfAbsent(season, (x) -> new ArrayList<>());
        while(keys.size() <= stage) {
            keys.add(null);
        }
    }

    protected class SeasonToKeyFunction implements Function<K, String> {

        protected final SeasonKeyImpl<K, V, S> seasonKey;
        protected final int season, stage;

        public SeasonToKeyFunction(SeasonKeyImpl<K, V, S> seasonKey, int season, int stage) {
            this.seasonKey = seasonKey;
            this.season = season;
            this.stage = stage;

            Preconditions.checkArgument(this.season > 0, "Season must be > 0");
        }

        @Override
        public String apply(K k) {
            return ("seasons.season" + this.season + ".stage" + this.stage) + "." + this.seasonKey.keyFunction.apply(k);
        }
    }
}
