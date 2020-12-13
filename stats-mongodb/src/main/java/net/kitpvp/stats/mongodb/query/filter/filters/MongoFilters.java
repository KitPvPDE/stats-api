package net.kitpvp.stats.mongodb.query.filter.filters;

import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.keys.array.ArraySStatsKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import net.kitpvp.stats.query.filter.Comparison;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface MongoFilters {

    static MongoFilter uuid(UUID uuid) {
        return uuid(uuid, "_id");
    }

    static MongoFilter uuid(UUID uuid, String field) {
        return new UUIDFilter(uuid, field);
    }

    static <K, V> MongoFilter or(SStatsKey<V> statsKey, List<V> vs) {
        return or(statsKey, null, vs);
    }

    static <K, V> MongoFilter or(StatsKey<K, V> statsKey, K k, List<V> vs) {
        return new OrFilter<>(statsKey, k, vs);
    }

    static <K, V, U> MongoFilter or(SStatsKey<V> statsKey, Collection<U> vs, Function<U, V> function) {
        return or(statsKey, null, vs.stream().map(function).collect(Collectors.toList()));
    }

    static <K, V, U> MongoFilter or(StatsKey<K, V> statsKey, K k, Collection<U> vs, Function<U, V> function) {
        return new OrFilter<>(statsKey, k, vs.stream().map(function).collect(Collectors.toList()));
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

    static <K, X> MongoFilter all(ArrayStatsKey<K, X> statsKey, K key, List<X> xs) {
        return new AllFilter<>(statsKey, key, xs);
    }

    static <K, X> MongoFilter all(ArraySStatsKey<X> statsKey, List<X> xs) {
        return new AllFilter<>(statsKey, null, xs);
    }
}
