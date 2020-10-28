package net.kitpvp.api.stats.keys;

import net.kitpvp.api.stats.Stats;

public interface SeasonKey<K, V> {

    StatsKey<K, V> season(int season);

    default StatsKey<K, V> season() {
        return this.season(Stats.SEASON);
    }

    default StatsKey<K, V> alltime() {
        return this.season(0);
    }

}
