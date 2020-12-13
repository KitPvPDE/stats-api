package net.kitpvp.stats.keys.bool;

import net.kitpvp.stats.keys.SSeasonKey;
import net.kitpvp.stats.keys.SStageKey;

public interface BooleanSStageKey extends BooleanStageKey<Void>, SSeasonKey<Boolean>, SStageKey<Boolean>, BooleanSSeasonKey {

    @Override
    BooleanSStatsKey season(int season);

    @Override
    BooleanSStatsKey stage(int season, int stage);

    @Override
    BooleanSStatsKey season();

    @Override
    BooleanSStatsKey alltime();

    @Override
    BooleanSStatsKey stage();
}
