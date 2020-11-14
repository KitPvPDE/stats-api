package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncSSeasonKey;
import net.kitpvp.stats.keys.SStageKey;

public interface LongSStageKey extends LongStageKey<Void>, IncSSeasonKey<Long>, SStageKey<Long>, LongSSeasonKey {

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
