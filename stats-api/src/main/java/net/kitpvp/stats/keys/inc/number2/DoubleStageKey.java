package net.kitpvp.stats.keys.inc.number2;

import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.keys.inc.IncSeasonKey;
import net.kitpvp.stats.keys.inc.IncStageKey;
import net.kitpvp.stats.keys.inc.numbers.DoubleSeasonKey;
import net.kitpvp.stats.keys.inc.numbers.DoubleStatsKey;

public interface DoubleStageKey<K> extends DoubleSeasonKey<K>, IncStageKey<K, Double> {

    @Override
    DoubleStatsKey<K> season(int season);

    @Override
    default DoubleStatsKey<K> season() {
        return (DoubleStatsKey<K>) IncSeasonKey.super.season();
    }

    @Override
    default DoubleStatsKey<K> alltime() {
        return (DoubleStatsKey<K>) IncSeasonKey.super.alltime();
    }

    @Override
    DoubleStatsKey<K> stage(int season, int stage);
}
