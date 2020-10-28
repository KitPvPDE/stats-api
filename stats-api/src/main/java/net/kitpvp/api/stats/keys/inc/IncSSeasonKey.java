package net.kitpvp.api.stats.keys.inc;

public interface IncSSeasonKey<V> extends IncSeasonKey<Void, V> {

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
