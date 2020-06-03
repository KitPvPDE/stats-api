package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season;

import net.kitpvp.pluginapi.modules.stats.Stats;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;

public interface SeasonKey<K, V> {

    StatsKey<K, V> season(int season);

    default StatsKey<K, V> season() {
        return this.season(Stats.SEASON);
    }

    default StatsKey<K, V> alltime() {
        return this.season(0);
    }
}
