package net.kitpvp.stats.mongodb.model;

import net.kitpvp.stats.Key;
import net.kitpvp.stats.VoidKey;
import org.bson.conversions.Bson;

import java.util.List;

import static com.mongodb.assertions.Assertions.notNull;

public final class Sorts {

    public static Bson ascending(VoidKey statKey) {
        return com.mongodb.client.model.Sorts.ascending(statKey.key());
    }

    public static <K> Bson ascending(Key<K> statKey, K key) {
        return com.mongodb.client.model.Sorts.ascending(statKey.key(key));
    }

    public static Bson descending(VoidKey statKey) {
        return com.mongodb.client.model.Sorts.descending(statKey.key());
    }

    public static <K> Bson descending(Key<K> statKey, K key) {
        return com.mongodb.client.model.Sorts.descending(statKey.key(key));
    }

    public static Bson metaTextScore(VoidKey statKey) {
        return com.mongodb.client.model.Sorts.metaTextScore(statKey.key());
    }

    public static <K> Bson metaTextScore(Key<K> statKey, K key) {
        return com.mongodb.client.model.Sorts.metaTextScore(statKey.key(key));
    }

    public static Bson orderBy(Bson... sorts) {
        return com.mongodb.client.model.Sorts.orderBy(sorts);
    }

    public static Bson orderBy(List<? extends Bson> sorts) {
        notNull("sorts", sorts);
        return com.mongodb.client.model.Sorts.orderBy(sorts);
    }
}
