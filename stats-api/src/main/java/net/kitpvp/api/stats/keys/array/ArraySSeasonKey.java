package net.kitpvp.api.stats.keys.array;

import net.kitpvp.api.stats.season.Season;

import java.util.List;

public interface ArraySSeasonKey<X, V extends List<X>> extends ArraySeasonKey<Void, X, V> {

    @Override
    ArraySStatsKey<X, V> season(int season);

    @Override
    default ArraySStatsKey<X, V> season() {
        return this.season(Season.getSeason());
    }

    @Override
    default ArraySStatsKey<X, V> alltime() {
        return this.season(Season.ALLTIME);
    }
}
