package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;

public interface NumberKey<K, V> extends StatsKey<K, V> {

    V inc(V v1, V v2);

}
