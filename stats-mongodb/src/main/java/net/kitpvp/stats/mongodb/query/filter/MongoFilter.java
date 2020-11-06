package net.kitpvp.stats.mongodb.query.filter;

import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.filter.filters.CompareFilter;
import net.kitpvp.stats.mongodb.query.filter.filters.UUIDFilter;
import net.kitpvp.stats.query.filter.Filter;
import org.bson.Document;

import java.util.UUID;
import java.util.stream.Stream;

public interface MongoFilter extends Filter<MongoStatsReader> {

    MongoFilter append(MongoStatsReader statsReader);

    @Deprecated
    default Document filter() {
        MongoStatsReader statsReader = new MongoStatsReader(new Document());
        this.append(statsReader);
        return statsReader.source();
    }

    static MongoFilter uuid(UUID uuid) {
        return uuid(uuid, "_id");
    }

    static MongoFilter uuid(UUID uuid, String field) {
        return new UUIDFilter(uuid, field);
    }

    static <K, V> MongoFilter equals(StatsKey<K, V> key, K k, V v) {
        return compare(key, k, v, Operator.EQUALS);
    }

    static <K, V> MongoFilter greater(StatsKey<K, V> key, K k, V v) {
        return compare(key, k, v, Operator.GREATER);
    }

    static <K, V> MongoFilter compare(StatsKey<K, V> key, K k, V v, Operator comparison) {
        return new CompareFilter<>(key, k, v, comparison);
    }

    static Document filter(Filter<MongoStatsReader>[] filters) {
        MongoStatsReader statsReader = new MongoStatsReader(new Document());
        Stream.of(filters).forEach(mongoFilter -> mongoFilter.append(statsReader));
        return statsReader.source();
    }
}
