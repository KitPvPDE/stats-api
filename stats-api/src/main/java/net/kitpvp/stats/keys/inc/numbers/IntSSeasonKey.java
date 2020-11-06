package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.keys.inc.IncSSeasonKey;

public interface IntSSeasonKey extends IntSeasonKey<Void>, IncSSeasonKey<Integer> {

    @Override
    IntSStatsKey season(int season);

    @Override
    default IntSStatsKey season() {
        return (IntSStatsKey) IntSeasonKey.super.season();
    }

    @Override
    default IntSStatsKey alltime() {
        return (IntSStatsKey) IntSeasonKey.super.alltime();
    }
}
