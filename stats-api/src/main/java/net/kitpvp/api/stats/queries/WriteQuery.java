package net.kitpvp.api.stats.queries;

import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;

public interface WriteQuery {

    <K, V> WriteQuery when(StatsKey<K, V> statsKey, K k);

    default <V> WriteQuery when(SStatsKey<V> statsKey) {
        return this.when(statsKey, null);
    }
}
