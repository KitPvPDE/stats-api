package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;

import java.util.List;

public interface SListKey<V> extends ListKey<Void, V, List<V>>, SStatsKey<List<V>> { }
