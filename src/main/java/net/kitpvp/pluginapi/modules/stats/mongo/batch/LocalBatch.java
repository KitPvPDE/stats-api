package net.kitpvp.pluginapi.modules.stats.mongo.batch;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.local.LocalStats;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.ListKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.NumberKey;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class LocalBatch implements Batch<LocalStats> {

    private final LocalStats localStats;
    @Getter
    private final BatchAction defaultAction;

    @Override
    public <K, V> Batch<LocalStats> append(BatchAction action, StatsKey<K, V> statsKey, K k, V v) {
        switch(action){
            case SET:
                this.localStats.set(statsKey, k, v);
                break;
            case INC:
                if(statsKey instanceof NumberKey) {
                    NumberKey<K, V> numberKey = (NumberKey<K, V>) statsKey;
                    this.localStats.set(statsKey, k, numberKey.inc(this.localStats.syncLoad().getStatKey(statsKey, k), v));
                } else {
                    System.err.println("Cannot increment stats key " + statsKey);
                }
                break;
            case PULL:
            case PUSH:
                if(statsKey instanceof ListKey) {
                    ListKey<K, V, List<V>> listKey = (ListKey<K, V, List<V>>) statsKey;
                    List<V> current = this.localStats.syncLoad().getStatKey(listKey, k);
                      if(action == BatchAction.PULL)
                          listKey.pull(current, (List<V>) v);
                      else
                          listKey.push(current, (List<V>) v);
                } else {
                    System.err.println("Cannot push/pull from " + statsKey);
                }
                break;
        }
        return this;
    }

    /*
    public <K> Batch<LocalStats> append(BatchAction action, StatsKey<K, Long> statsKey, K k, Long v) {
        switch(action){
            case SET:
                this.localStats.set(statsKey, k, v);
                break;
            case INC:
                this.localStats.inc(statsKey, k, v, Long::sum);
                break;
            case PUSH:
            case PULL:
                System.err.println("Cannot PULL/PUSH non list types");
                break;
        }
        return this;
    }*/

    /*
    public <K> Batch<LocalStats> append(BatchAction action, StatsKey<K, Integer> statsKey, K k, Integer v) {
        switch(action){
            case SET:
                this.localStats.set(statsKey, k, v);
                break;
            case INC:
                this.localStats.inc(statsKey, k, v, Integer::sum);
                break;
            case PUSH:
            case PULL:
                System.err.println("Cannot PULL/PUSH non list types");
                break;
        }
        return this;
    }

    public <K, V, L extends List<V>> Batch<LocalStats> append(BatchAction action, StatsKey<K, L> statsKey, K k, L v) {
        switch(action){
            case SET:
                this.localStats.set(statsKey, k, v);
                break;
            case INC:
                System.err.println("Cannot INC list types");
                break;
            case PUSH:
                this.localStats.push(statsKey, k, v);
                break;
            case PULL:
                this.localStats.pull(statsKey, k, v);
                break;
        }
        return this;
    }*/

    @Override
    public LocalStats execute(boolean checkMain) {
        return this.localStats;
    }

    @Override
    public void executeAsync(Consumer<Void> callback, Executor executor) {
        executor.execute(() -> callback.accept(null));
    }

    @Override
    public int size() {
        return 0;
    }
}
