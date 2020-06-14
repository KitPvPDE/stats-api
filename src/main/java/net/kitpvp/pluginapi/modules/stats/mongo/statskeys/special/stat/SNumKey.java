package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.stat;

import lombok.RequiredArgsConstructor;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;
import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special.SNumberKey;
import org.bson.Document;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class SNumKey<V> implements SNumberKey<V>, SStatsKey<V> {

    private final SStatsKey<V> statsKey;
    private final BiFunction<V, V, V> increment;

    @Override
    public V inc(V v1, V v2) {
        return this.increment.apply(v1, v2);
    }

    @Override
    public V getDefault() {
        return this.statsKey.getDefault();
    }

    @Override
    public Document append(Document to, Void ignored, V v) {
        return this.statsKey.append(to, ignored, v);
    }

    @Override
    public String getKey(Void key) {
        return this.statsKey.getKey();
    }

    @Override
    public V apply(V v) {
        return this.statsKey.apply(v);
    }

}
