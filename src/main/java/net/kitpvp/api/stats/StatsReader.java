package net.kitpvp.api.stats;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface StatsReader {

    <V> V find(String key, V def);

    default <K, V> boolean containsKey(StatsKey<K, V> statsKey, K k) {
        return this.find(statsKey.getKey(k), null) != null;
    }

    default <K, V> Set<String> getStatKeys(StatsKey<K, V> statsKey, K k) {
        String key = statsKey.getKey(k);
        if(key.contains("."))
            key = key.substring(0, key.lastIndexOf('.'));

        Map<String, Object> map = this.find(key, new HashMap<>());
        return map.keySet();
    }

    default <K, V> V getStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.apply(this.find(statsKey.getKey(k), statsKey.getDefault()));
    }

    default <V> V getStatKey(SStatsKey<V> statsKey) {
        return this.getStatKey(statsKey, (Void)null);
    }

    default <K, V, X> X getStatKey(StatsKey<K, V> statsKey, K k, Function<V, X> function) {
        return function.apply(this.getStatKey(statsKey, k));
    }

    default <K, V, X> List<X> getStatKeyList(StatsKey<K, List<V>> statsKey, K k, Function<V, X> function) {
        return this.getStatKey(statsKey, k).stream().map(function).collect(Collectors.toList());
    }

    default <V, X> List<X> getStatKeyList(SStatsKey<List<V>> statsKey, Function<V, X> function) {
        return this.getStatKeyList(statsKey, null, function);
    }

    default <K, V> V getStatKey(SeasonKey<K, V> seasonKey, int season, K k) {
        return this.getStatKey(seasonKey.season(season), k);
    }

    default <V> V getStatKey(SSeasonKey<V> seasonKey, int season) {
        return this.getStatKey(seasonKey.season(season));
    }

    default <V, F> V funcStatKey(SStatsKey<F> statsKey, Function<F, V> function) {
        return function.apply(this.getStatKey(statsKey));
    }

    default <K, V> V rawStatKey(StatsKey<K, V> statsKey, K k) {
        return this.find(statsKey.getKey(k), statsKey.getDefault());
    }

    default <V> V rawStatKey(SStatsKey<V> statsKey) {
        return this.rawStatKey(statsKey, null);
    }

    default <K, V> V rawStatKey(SeasonKey<K, V> seasonKey, int season, K k) {
        return this.rawStatKey(seasonKey.season(season), k);
    }

    default <V> V rawStatKey(SSeasonKey<V> seasonKey, int season) {
        return this.rawStatKey(seasonKey.season(season));
    }
}
