package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.functions.season.VoidSeasonKeyFunction;
import net.kitpvp.stats.season.Season;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

abstract class VoidSeasonKeyImpl<V, S extends VoidStatsKey<V>> implements VoidSeasonKey<V> {

    protected final VoidKeyFunction keyFunction;
    protected final Supplier<V> defaultFunction;
    private final List<S> keys;

    VoidSeasonKeyImpl(@Nullable Supplier<V> defaultFunction, @NotNull VoidKeyFunction keyFunction) {
        this.keyFunction = keyFunction;
        this.defaultFunction = defaultFunction;
        this.keys = new ArrayList<>();
        for(int i = 0; i <= Season.getSeason(); i++) {
            this.keys.add(null);
        }
    }

    public VoidSeasonKeyImpl(@NotNull VoidKeyFunction keyFunction) {
        this(null, keyFunction);
    }

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

    protected abstract S createKey(VoidKeyFunction function);

    protected final VoidKeyFunction createKeyFunction(int season) {
        return new VoidSeasonKeyFunction(this.keyFunction, season);
    }

    private void checkCapacity(int season) {
        while(this.keys.size() <= season) {
            this.keys.add(null);
        }
    }

}
