package net.kitpvp.stats.keys.inc;

import net.kitpvp.stats.api.keys.AppendableIncKey;
import net.kitpvp.stats.keys.SeasonKey;

public interface IncSeasonKey<K, V> extends SeasonKey<K, V>, AppendableIncKey<K, V> {

    @Override
    IncStatsKey<K, V> season(int season);

    @Override
    default IncStatsKey<K, V> season() {
        return (IncStatsKey<K, V>) SeasonKey.super.season();
    }

    @Override
    default IncStatsKey<K, V> alltime() {
        return (IncStatsKey<K, V>) SeasonKey.super.alltime();
    }
}
