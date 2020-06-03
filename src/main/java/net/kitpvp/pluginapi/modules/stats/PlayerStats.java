package net.kitpvp.pluginapi.modules.stats;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.local.LocalStats;
import net.kitpvp.pluginapi.modules.stats.mongo.MongoStats;
import net.kitpvp.pluginapi.modules.stats.mongo.batch.BatchAction;
import net.kitpvp.pluginapi.modules.stats.mongo.batch.PlayerBatch;
import net.kitpvp.pluginapi.modules.stats.player.PlayerStatsReader;

import java.util.UUID;
import java.util.concurrent.Future;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class PlayerStats implements Stats {

    @Getter
    private final UUID playerId;
    @Getter
    private final MongoStats mongoStats;
    @Getter
    private final LocalStats localStats;

    @Override
    public PlayerBatch startBatch(BatchAction action) {
        return new PlayerBatch(action, this);
    }

    @Override
    public void load(Consumer<StatsReader> callback) {
        callback.accept(this.syncLoad());
    }

    @Override
    public StatsReader syncLoad() {
        return new PlayerStatsReader(this.localStats.syncLoad(), this.mongoStats.getLastLoaded());
    }
}
