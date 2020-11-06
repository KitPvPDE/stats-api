package net.kitpvp.stats.keys.set;

import net.kitpvp.stats.keys.SeasonKey;

import java.util.Set;

public interface SetSeasonKey<K, X> extends SeasonKey<K, Set<X>> {

    @Override
    SetStatsKey<K, X> season(int season);

    @Override
    default SetStatsKey<K, X> season() {
        return (SetStatsKey<K, X>) SeasonKey.super.season();
    }

    @Override
    default SetStatsKey<K, X> alltime() {
        return (SetStatsKey<K, X>) SeasonKey.super.alltime();
    }
}
