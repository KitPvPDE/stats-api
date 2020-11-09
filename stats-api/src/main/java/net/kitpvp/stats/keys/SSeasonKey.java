package net.kitpvp.stats.keys;

import net.kitpvp.stats.season.Season;

public interface SSeasonKey<V> extends SeasonKey<Void, V> {

    @Override
    SStatsKey<V> season(int season);

    default SStatsKey<V> season() {
        return this.season(Season.getSeason());
    }

    default SStatsKey<V> alltime() {
        return this.season(0);
    }

}
