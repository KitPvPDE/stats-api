package net.kitpvp.stats.keys.set;

import net.kitpvp.stats.season.Season;

import java.util.Set;

public interface SetSSeasonKey<X> extends SetSeasonKey<Void, X> {

    @Override
    SetSStatsKey<X> season(int season);

    @Override
    default SetSStatsKey<X> season() {
        return this.season(Season.getSeason());
    }

    @Override
    default SetSStatsKey<X> alltime() {
        return this.season(Season.ALLTIME);
    }
}
