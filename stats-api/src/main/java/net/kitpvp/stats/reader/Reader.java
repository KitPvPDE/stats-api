package net.kitpvp.stats.reader;

import net.kitpvp.stats.season.Season;
import net.kitpvp.stats.keys.StatsKey;

public interface Reader {

    int ALLTIME = Season.ALLTIME;

    <V> V find(String key, V def);

    default <K, V> V getStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.apply(this.find(statsKey.key(k), statsKey.def()));
    }

}
