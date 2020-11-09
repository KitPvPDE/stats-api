package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncSSeasonKey;

public interface LongSStageKey extends LongStageKey<Void>, IncSSeasonKey<Long> {

    @Override
    LongSStatsKey season(int season);

    @Override
    LongSStatsKey stage(int season, int stage);

    @Override
    default LongSStatsKey season() {
        return (LongSStatsKey) LongStageKey.super.season();
    }

    @Override
    default LongSStatsKey alltime() {
        return (LongSStatsKey) LongStageKey.super.alltime();
    }

    @Override
    default LongSStatsKey stage() {
        return (LongSStatsKey) LongStageKey.super.stage();
    }
}
