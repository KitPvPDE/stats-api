package net.kitpvp.stats.mongodb.query.filter.filters;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import org.bson.Document;

import java.util.List;

@RequiredArgsConstructor
class OrFilter<K, V> implements MongoFilter {

    private final StatsKey<K, V> statsKey;
    private final K key;
    private final List<V> values;

    @Override
    public MongoFilter append(MongoStatsReader statsReader) {
        statsReader.source().append(this.statsKey.key(this.key), new Document("$in", this.values));
        return this;
    }
}
