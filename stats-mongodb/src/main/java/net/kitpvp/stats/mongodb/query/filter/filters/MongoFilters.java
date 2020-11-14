package net.kitpvp.stats.mongodb.query.filter.filters;

import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import net.kitpvp.stats.query.filter.Comparison;

import java.util.UUID;

public interface MongoFilters {

    static MongoFilter uuid(UUID uuid) {
        return uuid(uuid, "_id");
    }

    static MongoFilter uuid(UUID uuid, String field) {
        return new UUIDFilter(uuid, field);
    }

    static <K, V> MongoFilter equals(StatsKey<K, V> key, K k, V v) {
        return compare(key, k, v, Comparison.EQUALS);
    }

    static <K, V> MongoFilter greater(StatsKey<K, V> key, K k, V v) {
        return compare(key, k, v, Comparison.GREATER);
    }

    static <K, V> MongoFilter compare(StatsKey<K, V> key, K k, V v, Comparison comparison) {
        return new CompareFilter<>(key, k, v, comparison);
    }

}
