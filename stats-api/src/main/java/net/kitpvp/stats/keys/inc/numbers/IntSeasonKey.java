package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.keys.inc.IncSeasonKey;

public interface IntSeasonKey<K> extends IncSeasonKey<K, Integer> {

    @Override
    IntStatsKey<K> season(int season);

    @Override
    default IntStatsKey<K> season() {
        return (IntStatsKey<K>) IncSeasonKey.super.season();
    }

    @Override
    default IntStatsKey<K> alltime() {
        return (IntStatsKey<K>) IncSeasonKey.super.alltime();
    }
}
