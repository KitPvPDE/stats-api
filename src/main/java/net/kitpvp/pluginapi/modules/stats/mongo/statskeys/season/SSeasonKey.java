package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;

public interface SSeasonKey<V> extends SeasonKey<Void, V> {

    @Override
    SStatsKey<V> season(int season);

    SStatsKey<V> season();

    SStatsKey<V> alltime();
}
