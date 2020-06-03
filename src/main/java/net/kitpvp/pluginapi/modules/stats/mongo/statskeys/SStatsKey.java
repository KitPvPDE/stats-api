package net.kitpvp.pluginapi.modules.stats.mongo.statskeys;

import org.bson.Document;

public interface SStatsKey<V> extends StatsKey<Void, V> {

    default String getKey() {
        return this.getKey(null);
    }

    default Document append(Document to, V v) {
        return this.append(to, null, v);
    }

}
