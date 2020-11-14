package net.kitpvp.stats.mongodb.query.sort.sorts;

import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.mongodb.query.sort.MongoSort;
import net.kitpvp.stats.mongodb.query.sort.sorts.SortFilter;
import net.kitpvp.stats.query.sort.Order;

public interface MongoSorts {

    static <K, V> MongoSort descending(StatsKey<K, V> statsKey, K key) {
        return sort(statsKey, key, Order.DESCENDING);
    }

    static <V> MongoSort descending(SStatsKey<V> statsKey) {
        return sort(statsKey, null, Order.DESCENDING);
    }

    static <K, V> MongoSort ascending(StatsKey<K, V> statsKey, K key) {
        return sort(statsKey, key, Order.ASCENDING);
    }

    static <V> MongoSort ascending(SStatsKey<V> statsKey) {
        return sort(statsKey, null, Order.ASCENDING);
    }

    static <K, V> MongoSort sort(StatsKey<K, V> statsKey, K key, Order sort) {
        return new SortFilter<>(statsKey, key, sort);
    }
}
