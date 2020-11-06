package net.kitpvp.stats.keys.array;

import net.kitpvp.stats.season.Season;

public interface ArraySSeasonKey<X> extends ArraySeasonKey<Void, X> {

    @Override
    ArraySStatsKey<X> season(int season);

    @Override
    default ArraySStatsKey<X> season() {
        return this.season(Season.getSeason());
    }

    @Override
    default ArraySStatsKey<X> alltime() {
        return this.season(Season.ALLTIME);
    }
}
