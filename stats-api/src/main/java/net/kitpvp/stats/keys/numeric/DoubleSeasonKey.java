package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncSeasonKey;

public interface DoubleSeasonKey<K> extends IncSeasonKey<K, Double> {

    @Override
    DoubleStatsKey<K> season(int season);

    @Override
    default DoubleStatsKey<K> season() {
        return (DoubleStatsKey<K>) IncSeasonKey.super.season();
    }

    @Override
    default DoubleStatsKey<K> alltime() {
        return (DoubleStatsKey<K>) IncSeasonKey.super.alltime();
    }
}
