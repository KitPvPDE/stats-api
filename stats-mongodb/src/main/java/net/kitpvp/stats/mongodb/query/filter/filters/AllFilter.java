package net.kitpvp.stats.mongodb.query.filter.filters;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import org.bson.Document;

import java.util.List;

@RequiredArgsConstructor
class AllFilter<K, X> implements MongoFilter {

    private final ArrayStatsKey<K, X> statsKey;
    private final K key;
    private final List<X> values;

    @Override
    public MongoFilter append(MongoStatsReader statsReader) {
        statsReader.source().append(this.statsKey.key(this.key), new Document("$all", this.values));
        return this;
    }
}
