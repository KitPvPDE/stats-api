package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat;

import net.kitpvp.pluginapi.modules.stats.Stats;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.stat.SNumKey;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class SSeasonStatKey<V> extends SeasonStatKey<Void, V> implements SSeasonKey<V> {

    protected final String key;

    public SSeasonStatKey(V def, String key, Function<V, V> toValue) {
        super(def, null, toValue);
        this.key = key;
    }

    public SSeasonStatKey(Supplier<V> toDefault, String key, Function<V, V> toValue) {
        super(toDefault, null, toValue);
        this.key = key;
    }

    public SSeasonStatKey(V def, String key, Function<V, V> toValue, BiFunction<V, V, V> addFunction) {
        super(def, null, toValue, addFunction);
        this.key = key;
    }

    @Override
    public SStatsKey<V> season(int season) {
        return (SStatsKey<V>) super.season(season);
    }

    @Override
    public SStatsKey<V> season() {
        return this.season(Stats.SEASON);
    }

    @Override
    public SStatsKey<V> alltime() {
        return this.season(0);
    }

    @Override
    protected StatsKey<Void, V> createKey(V def, Function<Void, String> function, Function<V, V> toValue) {
        if(this.addFunction != null) {
            return new SNumKey<>(new SStatKey<V>(def, function, toValue), this.addFunction);
        }
        return new SStatKey<>(def, function, toValue);
    }

    @Override
    protected StatsKey<Void, V> createKey(Supplier<V> toDefault, Function<Void, String> function, Function<V, V> toValue) {
        if(this.addFunction != null) {
            return new SNumKey<>(new SStatKey<V>(toDefault, function, toValue), this.addFunction);
        }
        return new SStatKey<>(toDefault, function, toValue);
    }

    @Override
    public SSeasonToKeyFunction<V> createKeyFunction(int season) {
        return new SSeasonToKeyFunction<>(this, season);
    }
}
