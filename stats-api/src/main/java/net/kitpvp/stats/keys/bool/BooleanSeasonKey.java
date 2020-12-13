package net.kitpvp.stats.keys.bool;

import net.kitpvp.stats.keys.SeasonKey;

public interface BooleanSeasonKey<K> extends SeasonKey<K, Boolean> {

    @Override
    BooleanStatsKey<K> season(int season);

    @Override
    BooleanStatsKey<K> season();

    @Override
    BooleanStatsKey<K> alltime();
}
