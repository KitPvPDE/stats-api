package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncSSeasonKey;
import net.kitpvp.stats.keys.SStageKey;

public interface IntSStageKey extends IntStageKey<Void>, IncSSeasonKey<Integer>, SStageKey<Integer>, IntSSeasonKey {

    @Override
    IntSStatsKey season(int season);

    @Override
    IntSStatsKey stage(int season, int stage);

    @Override
    IntSStatsKey season();

    @Override
    IntSStatsKey alltime();

    @Override
    IntSStatsKey stage();
}
