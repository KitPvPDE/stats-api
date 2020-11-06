package net.kitpvp.stats.keys;

import net.kitpvp.stats.api.keys.AppendableKey;
import net.kitpvp.stats.api.functions.TriConsumer;
import net.kitpvp.stats.season.Season;

public interface SeasonKey<K, V> extends AppendableKey<K, V> {

    StatsKey<K, V> season(int season);

    default StatsKey<K, V> season() {
        return this.season(Season.getSeason());
    }

    default StatsKey<K, V> alltime() {
        return this.season(0);
    }

    @Override
    default void append(K key, V value, TriConsumer<StatsKey<K, V>, K, V> function) {
        this.season().append(key, value, function);

        this.alltime().append(key, value, function);
    }
}
