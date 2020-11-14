package net.kitpvp.stats.mongodb.query.sort.sorts;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.sort.MongoSort;
import net.kitpvp.stats.query.sort.Order;

@RequiredArgsConstructor
public class SortFilter<K, V> implements MongoSort {

    private final StatsKey<K, V> statsKey;
    private final K key;
    private final Order operator;

    @Override
    public MongoSort append(MongoStatsReader statsReader) {
        statsReader.source().append(this.statsKey.key(this.key), this.operator.getOrder());
        return this;
    }
}
