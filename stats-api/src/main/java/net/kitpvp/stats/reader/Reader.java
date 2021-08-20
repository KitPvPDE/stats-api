package net.kitpvp.stats.reader;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.Key;
import net.kitpvp.stats.season.Season;

import java.util.Optional;

public interface Reader {

    int ALLTIME = Season.ALLTIME;

    <V> V find(String key, V def, Class<V> type);

    <K> Optional<StatsReader> getStatReader(Key<K> statsKey, K key);

    @SuppressWarnings("unchecked")
    default <V> V find(String key, V def) {
        return (V) this.find(key, def, Object.class);
    }
}
