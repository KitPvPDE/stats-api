package net.kitpvp.pluginapi.modules.stats.modules;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat.StatKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface ClanStats {

    SStatsKey<String> NAME =
            StatKeys.newStatsKey("name", "");
    SStatsKey<String> DESCRIPTION =
            StatKeys.newStatsKey("description", "");
    StatsKey<String, String> USER_GROUP =
            StatKeys.newStatsKey("users", Function.identity(), null, "");
    SStatsKey<List<String>> INVITES =
            StatKeys.newStatsKey("invites", ArrayList::new);

}
