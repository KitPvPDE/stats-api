package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncSeasonKey;

public interface IntSeasonKey<K> extends IncSeasonKey<K, Integer> {

    @Override
    IntStatsKey<K> season(int season);

    @Override
    IntStatsKey<K> season();

    @Override
    IntStatsKey<K> alltime();
}
