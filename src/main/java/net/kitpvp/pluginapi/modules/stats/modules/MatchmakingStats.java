package net.kitpvp.pluginapi.modules.stats.modules;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat.StatKeys;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public interface MatchmakingStats {

    SSeasonKey<Integer> ELO =
            StatKeys.newSeasonKey("1vs1.elo.normal", 0, 1000);
    SSeasonKey<Integer> SUMO =
            StatKeys.newSeasonKey("1vs1.elo.sumo", 0, 1000);
    SStatsKey<String> SELECTED_KIT =
            StatKeys.newStatsKey("selected.uuid", "");
    SStatsKey<Integer> SELECTED_INDEX =
            StatKeys.newStatsKey("selected.index", 0);
    SStatsKey<Integer> OWN_INDEX =
            StatKeys.newStatsKey("selected.own.index", 0);
    SStatsKey<List<Document>> OWN_KITS =
            StatKeys.newStatsKey("kits", ArrayList::new);

    SeasonKey<String, Integer> LEGACY_ELO =
            StatKeys.newSeasonKey("1vs1.", Function.identity(), ".elo", 0, 1000);

}
