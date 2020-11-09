package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncSSeasonKey;

public interface LongSSeasonKey extends LongSeasonKey<Void>, IncSSeasonKey<Long> {

    @Override
    LongSStatsKey season(int season);

    @Override
    default LongSStatsKey season() {
        return (LongSStatsKey) LongSeasonKey.super.season();
    }

    @Override
    default LongSStatsKey alltime() {
        return (LongSStatsKey) LongSeasonKey.super.alltime();
    }
}
