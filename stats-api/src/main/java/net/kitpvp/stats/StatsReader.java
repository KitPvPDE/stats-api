package net.kitpvp.stats;

import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.reader.IntReader;
import net.kitpvp.stats.reader.LongReader;

public interface StatsReader extends IntReader, LongReader {

    <V> V find(String key, V def);

    default <K, V> V readStatKey(StatsKey<K, V> statsKey, K k) {
        return this.find(statsKey.key(k), statsKey.def());
    }

    default <K, V> V getStatKey(StatsKey<K, V> statsKey, K k) {
        return statsKey.apply(this.find(statsKey.key(k), statsKey.def()));
    }

}
