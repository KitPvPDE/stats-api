package net.kitpvp.pluginapi.modules.stats.mongo.batch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.mongo.MongoStats;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import org.bson.Document;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class MongoBatch implements Batch<MongoStats> {

    private final MongoStats stats;
    @Getter
    private final BatchAction defaultAction;
    private final Document set = new Document(), inc = new Document(), push = new Document(), pull = new Document();

    @Override
    public int size() {
        return this.set.size() + this.inc.size() + this.push.size() + this.pull.size();
    }

    public MongoStats execute() {
        return this.execute(true);
    }

    public MongoStats execute(boolean checkSync) {
        Document result = new Document();

        if(this.set.size() > 0)
            result.append("$set", this.set);

        if(this.inc.size() > 0)
            result.append("$inc", this.inc);

        if(this.push.size() > 0)
            result.append("$push", this.push);

        if(this.pull.size() > 0)
            result.append("$pull", this.pull);

        this.stats.execute(result, checkSync);
        return this.stats;
    }

    public void executeAsync(Consumer<Void> callback, Executor executor) {
        Document result = new Document();

        if(this.set.size() > 0)
            result.append("$set", this.set);

        if(this.inc.size() > 0)
            result.append("$inc", this.inc);

        if(this.push.size() > 0)
            result.append("$push", this.push);

        if(this.pull.size() > 0)
            result.append("$pull", this.pull);

        this.stats.executeAsync(result, callback, executor);
    }

    @Override
    public <K, V> Batch<MongoStats> append(BatchAction action, StatsKey<K, V> statsKey, K k, V v) {
        switch(action){
            case INC:
                statsKey.append(this.inc, k, v);
                break;
            case SET:
                statsKey.append(this.set, k, v);
                break;
            case PUSH:
                Document $each = new Document("$each", v);
                this.push.append(statsKey.getKey(k), $each);
                break;
            case PULL:
                Document $in = new Document("$in", v);
                this.pull.append(statsKey.getKey(k), $in);
                break;
        }
        return this;
    }
}
