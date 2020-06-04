package net.kitpvp.pluginapi.modules.stats;

import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.pluginapi.modules.stats.mongo.batch.Batch;
import net.kitpvp.pluginapi.modules.stats.mongo.batch.BatchAction;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.NumberKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat.StatKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Stats {

    int SEASON = 3;

    void load(Consumer<StatsReader> callback, Executor executor);

    StatsReader syncLoad();

    UUID getPlayerId();

    Batch<? extends Stats> startBatch(BatchAction action);

    default boolean isLoaded() {
        return true;
    }

    default void load(Consumer<StatsReader> callback) {
        this.load(callback, Executors.DIRECT);
    }

    default <K, V> Batch<? extends Stats> startBatch(BatchAction action, StatsKey<K, V> statsKey, K k, V v) {
        return this.startBatch(action).append(statsKey, k, v);
    }

    default <V> Batch<? extends Stats> startBatch(BatchAction action, SStatsKey<V> statsKey, V v) {
        return this.startBatch(action).append(statsKey, v);
    }

    default <K, V> Batch<? extends Stats> startBatch(BatchAction action, SeasonKey<K, V> seasonKey, K k, V v) {
        return this.startBatch(action).
                append(seasonKey.alltime(), k, v).
                append(seasonKey.season(), k, v);
    }

    default <V> Batch<? extends Stats> startBatch(BatchAction action, SSeasonKey<V> seasonKey, V v) {
        return this.startBatch(action, seasonKey, null, v);
    }

    default <K, V> void executeBatchAsync(BatchAction action, StatsKey<K, V> statsKey, K k, V v, Consumer<Void> callback) {
        this.startBatch(action).append(statsKey, k, v).executeAsync(callback);
    }

    SStatsKey<String> MONGO_ID =
            StatKeys.newStatsKey("_id", null);
    SStatsKey<List<Long>> ACHIEVEMENT_LIST_KEY =
            StatKeys.newStatsKey("achievements", ArrayList::new);
    SStatsKey<List<String>> TITLES_KEY =
            StatKeys.newStatsKey("titles", ArrayList::new);
    StatsKey<String, String> SETTINGS_KEY =
            StatKeys.newStatsKey("misc.settings", Function.identity(), null, "");
    StatsKey<Setting, String> SETTINGS =
            StatKeys.newStatsKey("misc.settings", Setting::getSettingsKey, null, "");
    SStatsKey<List<String>> BLOCKED_PLAYERS =
            StatKeys.newStatsKey("misc.blocked", ArrayList::new);

    // KITPVP
    SSeasonKey<Long> KITPVP_KILLS =
            StatKeys.newSeasonKey("kitpvp.kills", 0L);
    SSeasonKey<Long> KITPVP_DEATHS =
            StatKeys.newSeasonKey("kitpvp.deaths", 0L);
    SSeasonKey<Long> ONLINE_TIME =
            StatKeys.newSeasonKey("online", 0L);
    SeasonKey<String, Long> KITPVP_WARP_KILLS =
            StatKeys.newSeasonKey("kitpvp.kits.warps", String::toLowerCase, null, 0L);
    SeasonKey<String, Long> KITPVP_KIT_KILLS =
            StatKeys.newSeasonKey("kitpvp.kits.kills", String::toLowerCase, null, 0L);
    SeasonKey<String, Long> KITPVP_KIT_KILLSTREAKS =
            StatKeys.newSeasonKey("kitpvp.kits.streaks", String::toLowerCase, null, 0L);
    SStatsKey<Long> KITPVP_SAVED_KILLSTREAK =
            StatKeys.newStatsKey("kitpvp.saved.killstreak", 0L);
    StatsKey<String, Integer> KITPVP_PATH_EXP =
            StatKeys.newStatsKey("kits.paths", Function.identity(), "exp", 0);
    StatsKey<String, Integer> KITPVP_PATH_SELECTED_LEVEL =
            StatKeys.newStatsKey("kits.paths", Function.identity(), "selected", 0);
    SStatsKey<Long> KITPVP_LEVEL =
            StatKeys.newStatsKey("level", 1L, 0L, Long::sum);
    SStatsKey<Double> KITPVP_EXP =
            StatKeys.newStatsKey("exp", 0D);
    SStatsKey<Integer> KITPVP_XP_BOOSTER_LEVEL =
            StatKeys.newStatsKey("misc.booster.exp.amplifier", 0);
    SStatsKey<Long> KITPVP_XP_BOOSTER_DURATION =
            StatKeys.newStatsKey("misc.booster.exp.until", 0L);
    StatsKey<String, Long> KITPVP_XP_BOOSTER_PURCHASE_COUNT =
            StatKeys.newStatsKey("misc.booster.exp.count", Function.identity(), null, 0L);
    StatsKey<String, Long> KITPVP_EVENT_WINS =
            StatKeys.newStatsKey("misc.events", Function.identity(), "wins", 0L);
    StatsKey<String, Long> KITPVP_EVENT_KILLS =
            StatKeys.newStatsKey("misc.events", Function.identity(), "kills", 0L);
    StatsKey<String, Long> KITPVP_EVENT_DEATHS =
            StatKeys.newStatsKey("misc.events", Function.identity(), "deaths", 0L);
    SSeasonKey<Long> KITPVP_KILLSTREAK_RECORD =
            StatKeys.newSeasonKey("kitpvp.records.killstreak", 0L);

    // MATCHMAKING
    StatsKey<String, Integer> PLAYED_MATCHES =
            StatKeys.newStatsKey("seasons.season" + SEASON + ".1vs1", Function.identity(), "playedMatches", 0);
    StatsKey<String, Integer> PLAYED_MATCHES_LAST_SEASON =
            StatKeys.newStatsKey("seasons.season" + (SEASON - 1) + ".1vs1", Function.identity(), "playedMatches", 0);
    StatsKey<String, Integer> WON_MATCHES =
            StatKeys.newStatsKey("seasons.season" + SEASON + ".1vs1", Function.identity(), "wonMatches", 0);
    StatsKey<String, Integer> LOST_MATCHES =
            StatKeys.newStatsKey("seasons.season" + SEASON + ".1vs1", Function.identity(), "lostMatches", 0);
    StatsKey<String, Integer> MATCHMAKING_KILLS =
            StatKeys.newStatsKey("seasons.season" + SEASON + ".1vs1", Function.identity(), "kills", 0);
    StatsKey<String, Integer> MATCHMAKING_DEATHS =
            StatKeys.newStatsKey("seasons.season" + SEASON + ".1vs1", Function.identity(), "deaths", 0);
    SStatsKey<Long> BALANCE =
            StatKeys.newStatsKey("credits", 0L);
    SStatsKey<List<String>> MATCHMAKING_BANNED_KITS =
            StatKeys.newStatsKey("misc.1vs1.bannedKits", ArrayList::new);
    StatsKey<String, List<String>> MATCHMAKING_BANNED_MAPS =
            StatKeys.newStatsKey("misc.1vs1.bannedMaps", Function.identity(), null, ArrayList::new);
    SStatsKey<List<String>> MATCHMAKING_LAST_OPPONENTS =
            StatKeys.newStatsKey("misc.1vs1.previousOpponents", ArrayList::new);
    SStatsKey<Integer> MATCHMAKING_LAST_OPPONENTS_STREAK =
            StatKeys.newStatsKey("misc.1vs1.previousOpponentsStreak", 0);

    // TRAINING
    StatsKey<String, Long> TRAINING_RECORDS =
            StatKeys.newStatsKey("alltime.training", Function.identity(), "record", -1L);
    StatsKey<String, Long> TRAINING_ITERATIONS =
            StatKeys.newStatsKey("alltime.training", Function.identity(), "tries", 0L);
    StatsKey<String, Long> TRAINING_STREAK =
            StatKeys.newStatsKey("alltime.training", Function.identity(), "streak", 0L);

    // MISC
    SStatsKey<Long> WALKED_BLOCKS =
            StatKeys.newStatsKey("misc.useless.walked", 0L);
    SStatsKey<Long> SOUPS_EATEN =
            StatKeys.newStatsKey("misc.useless.soups", 0L);
    SStatsKey<Long> AFK_TIME =
            StatKeys.newStatsKey("misc.useless.afk", 0L);
    SStatsKey<Long> JOIN_TIME =
            StatKeys.newStatsKey("misc.useless.joined", -1L);

    // DISCORD
    SStatsKey<Long> DISCORD_ID =
            StatKeys.newStatsKey("discord.id", -1L);
    SStatsKey<String> DISCORD_CODE =
            StatKeys.newStatsKey("discord.code", "");
    StatsKey<String, String> DISCORD_PLATFORMS =
            StatKeys.newStatsKey("discord.platforms.", Function.identity(), null, "");

}
