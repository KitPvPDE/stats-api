package net.kitpvp.pluginapi.modules.stats.mongo.batch;

import lombok.Getter;
import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.pluginapi.modules.stats.PlayerStats;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class PlayerBatch implements Batch<PlayerStats> {

    @Getter
    private final BatchAction defaultAction;
    private final PlayerStats playerStats;
    private final MongoBatch mongoBatch;
    private final LocalBatch localBatch;

    public PlayerBatch(BatchAction defaultAction, PlayerStats playerStats) {
        this.defaultAction = defaultAction;
        this.playerStats = playerStats;

        this.mongoBatch = this.playerStats.getMongoStats().startBatch(defaultAction);
        this.localBatch = this.playerStats.getLocalStats().startBatch(defaultAction);
    }

    @Override
    public int size() {
        return this.mongoBatch.size();
    }

    @Override
    public void clear() {
        this.mongoBatch.clear();
    }

    @Override
    public PlayerStats execute(boolean checkMain) {
        this.localBatch.execute(checkMain);
        this.mongoBatch.execute(checkMain);
        return this.playerStats;
    }

    @Override
    public void executeAsync(Consumer<Void> callback, Executor executor) {
        this.localBatch.executeAsync((ignored) -> {
            this.mongoBatch.executeAsync(callback, executor);
        }, executor);
    }

    @Override
    public <K, V> Batch<PlayerStats> append(BatchAction action, StatsKey<K, V> statsKey, K k, V v) {
        this.mongoBatch.append(action, statsKey, k, v);
        this.localBatch.append(action, statsKey, k, v);

        return this;
    }
}
