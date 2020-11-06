package net.kitpvp.stats.mongodb.query.filter.filters;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.filter.Operator;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import org.bson.Document;

@RequiredArgsConstructor
public class CompareFilter<K, V> implements MongoFilter {

    private final StatsKey<K, V> statsKey;
    private final K k;
    private final V v;
    private final Operator comparison;

    @Override
    public MongoFilter append(MongoStatsReader statsReader) {
        statsReader.source().append(this.statsKey.key(this.k), new Document(this.comparison.command(), this.v));
        return this;
    }
}
