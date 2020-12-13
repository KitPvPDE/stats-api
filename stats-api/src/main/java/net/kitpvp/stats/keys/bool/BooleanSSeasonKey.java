package net.kitpvp.stats.keys.bool;

import net.kitpvp.stats.keys.SSeasonKey;

public interface BooleanSSeasonKey extends BooleanSeasonKey<Void>, SSeasonKey<Boolean> {

    @Override
    BooleanSStatsKey season(int season);

    @Override
    BooleanSStatsKey season();

    @Override
    BooleanSStatsKey alltime();
}
