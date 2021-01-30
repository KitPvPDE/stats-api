package net.kitpvp.stats.mongodb.query.filter.filters;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.keys.array.ArrayStatsKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import org.bson.Document;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
class AnyFilter<K, X> implements MongoFilter {

    private final StatsKey<K, ? extends Collection<? extends X>> statsKey;
    //private final ArrayStatsKey<K, X> statsKey;
    private final K key;
    private final Collection<? extends X> values;

    @Override
    public MongoFilter append(MongoStatsReader statsReader) {
        statsReader.source().append(this.statsKey.key(this.key), new Document("$elemMatch", new Document("$in", this.values)));
        return this;
    }
}
