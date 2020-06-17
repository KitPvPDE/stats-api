package net.kitpvp.api.stats;

import net.kitpvp.api.stats.keys.SSeasonKey;
import net.kitpvp.api.stats.keys.SStatsKey;
import net.kitpvp.api.stats.keys.SeasonKey;
import net.kitpvp.api.stats.keys.StatsKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface StatsReader {

    <V> V find(String key, V def);

    // <- contains methods ->

    default <V> boolean containsKey(SStatsKey<V> statsKey) {
        return this.containsKey(statsKey, null);
    }

    default <K, V> boolean containsKey(StatsKey<K, V> statsKey, K k) {
        return this.find(statsKey.getKey(k), null) != null;
    }

    // <- get methods ->

    default <V> V getStatKey(SStatsKey<V> statsKey) {
        return this.getStatKey(statsKey, (Void)null);
    }

    default <K, V> V getStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.apply(this.find(statsKey.getKey(k), statsKey.getDefault()));
    }

    default <V, U> U getStatKey(SStatsKey<V> statsKey, Function<V, U> function) {
        return this.getStatKey(statsKey, null, function);
    }

    default <K, V, X> X getStatKey(StatsKey<K, V> statsKey, K k, Function<V, X> function) {
        return function.apply(this.getStatKey(statsKey, k));
    }

    // <- season get methods ->

    default <V> V getStatKey(SSeasonKey<V> seasonKey, int season) {
        return this.getStatKey(seasonKey, season, null);
    }

    default <K, V> V getStatKey(SeasonKey<K, V> seasonKey, int season, K k) {
        return this.getStatKey(seasonKey.season(season), k);
    }

    // <- raw get methods ->

    default <K, V> V getRaw(StatsKey<K, V> statsKey, K k) {
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


    // <- get keys methods ->

    default <V> Set<String> getStatKeys(SStatsKey<V> statsKey) {
        return this.getStatKeys(statsKey, null);
    }

    default <K, V> Set<String> getStatKeys(StatsKey<K, V> statsKey, K k) {
        String key = statsKey.getKey(k);
        if(key.contains("."))
            key = key.substring(0, key.lastIndexOf('.'));

        Map<String, Object> map = this.find(key, new HashMap<>());
        return map.keySet();
    }
}
