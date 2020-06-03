package net.kitpvp.pluginapi.modules.stats.local;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.Stats;
import net.kitpvp.pluginapi.modules.stats.StatsReader;
import net.kitpvp.pluginapi.modules.stats.mongo.batch.BatchAction;
import net.kitpvp.pluginapi.modules.stats.mongo.batch.LocalBatch;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class LocalStats implements Stats {

    @Getter
    private final UUID playerId;
    private final StatsReader reader = new LocalStatsReader(this);
    private Map<String, Object> storage = new ConcurrentHashMap<>();

    @Override
    public LocalBatch startBatch(BatchAction action) {
        return new LocalBatch(this, action);
    }

    public void load(Consumer<StatsReader> callback) {
        throw new UnsupportedOperationException("Attempted to load local stats asynchronously");
    }

    public StatsReader syncLoad() {
        return this.reader;
    }

    public <K, V> void set(StatsKey<K, V> statsKey, K k, V v) {
        String key = statsKey.getKey(k);

        this.storage.put(key, v);
    }

    public <K, V extends Number> void inc(StatsKey<K, V> statsKey, K k, V v, BiFunction<V, V, V> inc) {
        String key = statsKey.getKey(k);

        V current = this.reader.getStatKey(statsKey, k);

        this.storage.put(key, inc.apply(current, v));
    }

    public <K, V, L extends List<V>> void push(StatsKey<K, L> statsKey, K k, L v) {
        L current = this.reader.getStatKey(statsKey, k);
        if(current == null)
            return;

        current.addAll(v);
    }

    public <K, V, L extends List<V>> void pull(StatsKey<K, L> statsKey, K k, L v) {
        L current = this.reader.getStatKey(statsKey, k);
        if(current == null)
            return;

        current.removeAll(v);
    }

    @RequiredArgsConstructor
    private static class LocalStatsReader implements StatsReader {

        private final LocalStats stats;

        @Override
        public <V> V find(String key, V def) {
            return this.stats.getInternal(key, def);
        }
    }

    private <T> T getInternal(String key, T defaultValue) {
        if(storage.containsKey(key)){
            try{
                return (T) this.storage.get(key);
            }catch(Throwable t){
                t.printStackTrace();

                return defaultValue;
            }
        }
        return defaultValue;
    }

}
