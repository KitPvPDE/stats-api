package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncStageKey;
import net.kitpvp.stats.season.Season;

public interface IntStageKey<K> extends IntSeasonKey<K>, IncStageKey<K, Integer> {

    @Override
    IntStatsKey<K> season(int season);

    @Override
    IntStatsKey<K> season();

    @Override
    IntStatsKey<K> alltime();

    @Override
    IntStatsKey<K> stage(int season, int stage);

    @Override
    IntStatsKey<K> stage();
}
