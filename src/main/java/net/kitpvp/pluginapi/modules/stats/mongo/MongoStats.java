package net.kitpvp.pluginapi.modules.stats.mongo;

import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import net.kitpvp.mongodbapi.MongoCollection;
import net.kitpvp.mongodbapi.async.Async;
import net.kitpvp.mongodbapi.async.Sync;
import net.kitpvp.pluginapi.modules.stats.Stats;
import net.kitpvp.pluginapi.modules.stats.StatsReader;
import net.kitpvp.pluginapi.modules.stats.mongo.batch.BatchAction;
import net.kitpvp.pluginapi.modules.stats.mongo.batch.MongoBatch;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SSeasonKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.season.SeasonKey;
import net.kitpvp.pluginapi.modules.stats.ranking.Ranking;
import org.bson.Document;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class MongoStats implements Stats {

    @Getter
    private final UUID playerId;
    @Getter
    private final MongoCollection statsCollection;
    @Getter
    private final Consumer<StatsReader> whenLoaded;
    @Getter
    private final MongoBatch disconnectBatch;
    @Getter
    private StatsReader lastLoaded;

    public MongoStats(UUID playerId, MongoCollection stats) {
        this.playerId = playerId;
        this.statsCollection = stats;
        this.whenLoaded = null;
        this.disconnectBatch = this.startBatch(BatchAction.INC);
    }

    public MongoStats(UUID playerId, MongoCollection statsCollection, Consumer<StatsReader> whenLoaded) {
        this.playerId = playerId;
        this.statsCollection = statsCollection;
        this.whenLoaded = whenLoaded;
        this.disconnectBatch = this.startBatch(BatchAction.INC);
    }

    public boolean isLoaded() {
        return this.lastLoaded != null;
    }

    public void load(Consumer<StatsReader> callback, Executor executor) {
        Async.run(new Runnable() {
            @Override
            public void run() {
                StatsReader statsReader = MongoStats.this.syncLoad();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.accept(statsReader);
                    }
                });
            }
        });
    }

    @Override
    public StatsReader syncLoad() {
        Document document = this.statsCollection.getCollection().find(new Document("_id", this.playerId.toString())).first();
        if(document == null)
            document = new Document();

        MongoStatsReader statsReader = new MongoStatsReader(document);
        if(this.whenLoaded != null){
            this.lastLoaded = statsReader;
            this.whenLoaded.accept(statsReader);
        }

        return statsReader;
    }

    public MongoBatch disconnectBatch() {
        return this.disconnectBatch;
    }

    public MongoBatch startBatch(BatchAction action) {
        return new MongoBatch(this, action);
    }

    public void execute(Document document, boolean checkSync) {
        if(checkSync)
            this.checkForMainThread();
        if(document == null || document.isEmpty())
            return;

        this.statsCollection.getCollection().updateOne(new Document("_id", getPlayerId().toString()), document, new UpdateOptions().upsert(true));
    }

    public void executeAsync(Document document, Consumer<Void> callback) {
        Async.run(new Runnable() {
            @Override
            public void run() {
                MongoStats.this.execute(document, false);

                callback.accept(null);
            }
        });
    }

    public <K, V extends Number> long calculateRanking(MongoStatsReader statsReader, StatsKey<K, V> statsKey, K k) {
        return Ranking.calculateRanking(this, statsReader, statsKey, k);
    }

    public <K, V extends Number> long calculateRanking(MongoStatsReader statsReader, SeasonKey<K, V> statsKey, K k, int season) {
        return Ranking.calculateRanking(this, statsReader, statsKey, k, season);
    }

    public <V extends Number> long calculateRanking(MongoStatsReader statsReader, SSeasonKey<V> statsKey, int season) {
        return Ranking.calculateRanking(this, statsReader, statsKey, season);
    }

    private void checkForMainThread() {
        if(Sync.isMainThread()){
            throw new IllegalStateException("Synchronous database action via main server thread");
        }
    }
}
