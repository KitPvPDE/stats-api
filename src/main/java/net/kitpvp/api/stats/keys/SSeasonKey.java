package net.kitpvp.api.stats.keys;

public interface SSeasonKey<V> extends SeasonKey<Void, V> {

    @Override
    SStatsKey<V> season(int season);

    SStatsKey<V> season();

    SStatsKey<V> alltime();

}
