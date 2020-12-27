package net.kitpvp.stats.mongodb.query.filter;

import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.query.filter.Filter;
import org.bson.Document;

import java.util.stream.Stream;

public interface MongoFilter extends Filter<MongoStatsReader> {

    MongoFilter append(MongoStatsReader statsReader);

    @Deprecated
    default Document filter() {
        MongoStatsReader statsReader = new MongoStatsReader(new Document());
        this.append(statsReader);
        return statsReader.source();
    }
}
