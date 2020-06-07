package net.kitpvp.pluginapi.modules.stats.modules;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat.StatKeys;

import java.util.function.Function;

public interface NoDamageStats {

    StatsKey<String, String> INVENTORY_SETTING =
            StatKeys.newStatsKey("misc.nodamage", Function.identity(), null, "");

}
