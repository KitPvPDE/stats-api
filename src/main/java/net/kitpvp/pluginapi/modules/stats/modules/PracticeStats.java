package net.kitpvp.pluginapi.modules.stats.modules;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat.StatKeys;

public interface PracticeStats {

    SSeasonKey<Long> EARLYHG_KILLS =
            StatKeys.newSeasonKey("kitpvp.kits.warps.earlyhg", 0L);
    SSeasonKey<Long> FFA_KILLS =
            StatKeys.newSeasonKey("kitpvp.kits.warps.ffa", 0L);
    SSeasonKey<Long> FEAST_KILLS =
            StatKeys.newSeasonKey("kitpvp.kits.warps.feast", 0L);

}
