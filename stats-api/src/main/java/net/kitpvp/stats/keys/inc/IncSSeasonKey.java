package net.kitpvp.stats.keys.inc;

import net.kitpvp.stats.keys.SSeasonKey;

public interface IncSSeasonKey<V> extends IncSeasonKey<Void, V>, SSeasonKey<V> {

    @Override
    IncSStatsKey<V> season(int season);

    @Override
    default IncSStatsKey<V> season() {
        return (IncSStatsKey<V>) IncSeasonKey.super.season();
    }

    @Override
    default IncSStatsKey<V> alltime() {
        return (IncSStatsKey<V>) IncSeasonKey.super.alltime();
    }
}
