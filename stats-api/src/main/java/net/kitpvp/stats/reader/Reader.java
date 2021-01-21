package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.season.Season;

public interface Reader {

    int ALLTIME = Season.ALLTIME;

    <V> V find(String key, V def);

    default <K, V> V getStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.apply(this.readStatKey(statsKey, k));
    }

    default <K, V> V readStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.extract(this, k);
    }
}
