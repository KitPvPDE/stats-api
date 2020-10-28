package net.kitpvp.api.stats.keys.array;

import net.kitpvp.api.stats.keys.SeasonKey;

import java.util.List;

public interface ArraySeasonKey<K, X, V extends List<X>> extends SeasonKey<K, V> {

    @Override
    ArrayStatsKey<K, X, V> season(int season);

    @Override
    default ArrayStatsKey<K, X, V> season() {
        return (ArrayStatsKey<K, X, V>) SeasonKey.super.season();
    }

    @Override
    default ArrayStatsKey<K, X, V> alltime() {
        return (ArrayStatsKey<K, X, V>) SeasonKey.super.alltime();
    }
}
