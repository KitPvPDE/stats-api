package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.functions.season.VoidStageKeyFunction;
import net.kitpvp.stats.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

abstract class VoidStageKeyImpl<V, S extends VoidStatsKey<V>> extends VoidSeasonKeyImpl<V, S> implements VoidStageKey<V> {

    private final Map<Integer, List<S>> seasonKeys;

    VoidStageKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull VoidKeyFunction keyFunction) {
        super(defaultFunction, keyFunction);
        this.seasonKeys = new HashMap<>();
    }

    VoidStageKeyImpl(@NotNull VoidKeyFunction keyFunction) {
        super(keyFunction);
        this.seasonKeys = new HashMap<>();
    }

    @Override
    public S stage(int season, int stage) {
        this.checkCapacity(season, stage);
        S key = this.seasonKeys.get(season).get(stage);
        if(key == null) {
            key = this.createKey(this.createKeyFunction(season, stage));
            this.seasonKeys.get(season).set(stage, key);
        }
        return key;
    }

    @Override
    public S stage() {
        return this.stage(Season.getSeason(), Season.getStage());
    }

    protected abstract S createKey(VoidKeyFunction function);

    protected final VoidKeyFunction createKeyFunction(int season, int stage) {
        return new VoidStageKeyFunction(this.keyFunction, season, stage);
    }

    private void checkCapacity(int season, int stage) {
        List<S> keys = this.seasonKeys.computeIfAbsent(season, (x) -> new ArrayList<>());
        while(keys.size() <= stage) {
            keys.add(null);
        }
    }
}
