package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat;

import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;

import java.util.function.Function;

@RequiredArgsConstructor
public class SeasonToKeyFunction<K, V> implements Function<K, String> {

    protected final SeasonStatKey<K, V> seasonKey;
    protected final int season;

    @Override
    public String apply(K k) {
        return (this.season == 0 ? "alltime" : "seasons.season" + this.season) + "." + this.seasonKey.toKey.apply(k);
    }
}
