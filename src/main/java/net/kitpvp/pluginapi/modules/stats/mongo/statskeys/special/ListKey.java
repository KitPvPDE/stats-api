package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.special;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.StatsKey;
import org.bson.Document;

import java.util.List;

public interface ListKey<K, V, X extends List<V>> extends StatsKey<K, X> {

    @Override
    X getDefault();

    @Override
    Document append(Document to, K k, X vs);

    @Override
    String getKey(K k);

    @Override
    X apply(X vs);

    default X push(X list, X push) {
        list.addAll(push);

        return list;
    }

    default X pull(X list, X pull) {
        list.removeAll(pull);

        return list;
    }
}
