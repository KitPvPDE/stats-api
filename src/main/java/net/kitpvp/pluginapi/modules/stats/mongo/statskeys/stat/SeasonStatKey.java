package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat;

import net.kitpvp.pluginapi.modules.stats.Stats;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

class SeasonStatKey<K, V> implements SeasonKey<K, V> {

    protected final ArrayList<StatsKey<K, V>> keys;
    protected final V def;
    protected final Supplier<V> toDefault;
    protected final Function<K, String> toKey;
    protected final Function<V, V> toValue;

    public SeasonStatKey(V def, Function<K, String> toKey, Function<V, V> toValue) {
        this.keys = new ArrayList<>();
        for(int i = 0; i <= Stats.SEASON; i++){
            this.keys.add(null);
        }
        this.def = def;
        this.toKey = toKey;
        this.toValue = toValue;
        this.toDefault = null;
    }

    public SeasonStatKey(Supplier<V> toDefault, Function<K, String> toKey, Function<V, V> toValue) {
        this.keys = new ArrayList<>();
        for(int i = 0; i <= Stats.SEASON; i++){
            this.keys.add(null);
        }
        this.def = null;
        this.toDefault = toDefault;
        this.toKey = toKey;
        this.toValue = toValue;
    }

    @Override
    public StatsKey<K, V> season(int season) {
        StatsKey<K, V> statsKey = this.keys.get(season);
        if(statsKey == null){
            if(this.def != null){
                statsKey = this.createKey(this.def, this.createKeyFunction(season), this.toValue);
            }else{
                statsKey = new StatKey<>(this.toDefault, this.createKeyFunction(season), this.toValue);
            }
            this.keys.set(season, statsKey);
        }
        return statsKey;
    }

    public SeasonToKeyFunction<K, V> createKeyFunction(int season) {
        return new SeasonToKeyFunction<>(this, season);
    }

    protected StatsKey<K, V> createKey(V def, Function<K, String> function, Function<V, V> toValue) {
        return new StatKey<>(def, function, toValue);
    }

    protected StatsKey<K, V> createKey(Supplier<V> toDefault, Function<K, String> function, Function<V, V> toValue) {
        return new StatKey<>(toDefault, function, toValue);
    }

    /*
    @RequiredArgsConstructor
    protected class SeasonToKeyFunction implements Function<K, String> {

        private final int season;

        @Override
        public String apply(K k) {
            return (this.season == 0 ? "alltime" : "seasons.season" + this.season) + "." + SeasonStatKey.this.toKey.apply(k);
        }
    }*/
}
