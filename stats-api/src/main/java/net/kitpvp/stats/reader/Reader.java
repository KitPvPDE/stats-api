package net.kitpvp.stats.reader;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.Key;
import net.kitpvp.stats.season.Season;

import java.util.Optional;

public interface Reader {

    int ALLTIME = Season.ALLTIME;

    <V> V find(String key, V def);

    <K> Optional<StatsReader> getStatReader(Key<K> statsKey, K key);

    /*
    default <K, V> V getStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.apply(this.readStatKey(statsKey, k));
    }

    default <K, V> V readStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.extract(this, k);
    }*/
}
