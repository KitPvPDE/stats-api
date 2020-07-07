package net.kitpvp.pluginapi.modules.stats.mongo.batch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.mongo.MongoStats;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import org.bson.Document;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public class MongoBatch implements Batch<MongoStats> {

    private final MongoStats stats;
    @Getter
    private final BatchAction defaultAction;
    private final Document[] actions = new Document[BatchAction.values().length];
    private final BatchStatsReader statsReader = new BatchStatsReader();

    public MongoBatch(MongoStats stats, BatchAction defaultAction) {
        this.stats = stats;
        this.defaultAction = defaultAction;

        for(int i = 0; i < actions.length; i++)
            actions[i] = new Document();
    }

    @Override
    public int size() {
        int size = 0;
        for(Document document : this.actions) {
            size += document.size();
        }
        return size;
    }

    @Override
    public void clear() {
        for(Document document : this.actions) {
            document.clear();
        }
    }

    public MongoStats execute() {
        return this.execute(true);
    }

    public MongoStats execute(boolean checkSync) {
        Document result = this.build();

        if(result.isEmpty())
            return this.stats;

        this.stats.execute(result, checkSync);
        return this.stats;
    }

    public void executeAsync(Consumer<Void> callback, Executor executor) {
        Document result = this.build();

        this.stats.executeAsync(result, callback, executor);
    }

    @Override
    public <K, V> Batch<MongoStats> append(BatchAction action, StatsKey<K, V> statsKey, K k, V v) {
        this.statsReader.setDatabase(this.actions[action.ordinal()]);
        action.append(this.statsReader, statsKey, k, v);
        return this;
    }

    private Document build() {
        Document result = new Document();

        for(BatchAction action : BatchAction.values()) {
            if(this.actions[action.ordinal()].isEmpty())
                continue;

            result.append("$" + action.getCommand(), this.actions[action.ordinal()]);
        }

        return result;
    }
}
