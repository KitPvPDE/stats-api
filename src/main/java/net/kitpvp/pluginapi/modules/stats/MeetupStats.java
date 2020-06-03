package net.kitpvp.pluginapi.modules.stats;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat.StatKeys;

public interface MeetupStats {

    SSeasonKey<Long> KILLS =
            StatKeys.newSeasonKey("meetup.kills", 0L);
    SSeasonKey<Long> DEATHS =
            StatKeys.newSeasonKey("meetup.deaths", 0L);
    SSeasonKey<Long> ROUNDS_PLAYED =
            StatKeys.newSeasonKey("meetup.roundsPlayed", 0L);
    SSeasonKey<Long> WINS =
            StatKeys.newSeasonKey("meetup.wins", 0L);
    SSeasonKey<Long> POINTS =
            StatKeys.newSeasonKey("meetup.points", 0L);
    SSeasonKey<Integer> RE_ROLL =
            StatKeys.newSeasonKey("meetup.reroll", 0);
}
