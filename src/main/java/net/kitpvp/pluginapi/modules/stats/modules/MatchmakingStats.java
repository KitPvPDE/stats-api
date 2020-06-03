package net.kitpvp.pluginapi.modules.stats.modules;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat.StatKeys;

public interface MatchmakingStats {

    SSeasonKey<Integer> ELO =
            StatKeys.newSeasonKey("1vs1.elo.normal", 1000, 0, Integer::sum);
    SSeasonKey<Integer> SUMO =
            StatKeys.newSeasonKey("1vs1.elo.sumo", 1000, 0, Integer::sum);
    SStatsKey<String> SELECTED_KIT =
            StatKeys.newStatsKey("selectedKit", "");

}
