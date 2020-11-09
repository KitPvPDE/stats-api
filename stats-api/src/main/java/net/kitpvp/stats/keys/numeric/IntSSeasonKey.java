package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncSSeasonKey;

public interface IntSSeasonKey extends IntSeasonKey<Void>, IncSSeasonKey<Integer> {

    @Override
    IntSStatsKey season(int season);

    @Override
    IntSStatsKey season();

    @Override
    IntSStatsKey alltime();
}
