package net.kitpvp.stats.keys.numeric;

import net.kitpvp.stats.keys.IncSeasonKey;

public interface LongSeasonKey<K> extends IncSeasonKey<K, Long> {

    @Override
    LongStatsKey<K> season(int season);

    @Override
    default LongStatsKey<K> season() {
        return (LongStatsKey<K>) IncSeasonKey.super.season();
    }

    @Override
    default LongStatsKey<K> alltime() {
        return (LongStatsKey<K>) IncSeasonKey.super.alltime();
    }
}
