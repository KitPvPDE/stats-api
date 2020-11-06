package net.kitpvp.stats.keys.inc.numbers;

import net.kitpvp.stats.keys.inc.IncStageKey;
import net.kitpvp.stats.keys.inc.numbers.LongSeasonKey;
import net.kitpvp.stats.keys.inc.numbers.LongStatsKey;
import net.kitpvp.stats.season.Season;

public interface LongStageKey<K> extends LongSeasonKey<K>, IncStageKey<K, Long> {

    @Override
    LongStatsKey<K> season(int season);

    @Override
    default LongStatsKey<K> season() {
        return (LongStatsKey<K>) LongSeasonKey.super.season();
    }

    @Override
    default LongStatsKey<K> alltime() {
        return (LongStatsKey<K>) LongSeasonKey.super.alltime();
    }

    @Override
    LongStatsKey<K> stage(int season, int stage);

    @Override
    default LongStatsKey<K> stage() {
        return this.stage(Season.getSeason(), Season.getStage());
    }
}
