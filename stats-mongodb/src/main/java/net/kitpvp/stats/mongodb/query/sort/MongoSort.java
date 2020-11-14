package net.kitpvp.stats.mongodb.query.sort;

import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.query.filter.Filter;
import net.kitpvp.stats.query.sort.Sort;
import org.bson.Document;

import java.util.stream.Stream;

public interface MongoSort extends Sort<MongoStatsReader> {

    @Override
    MongoSort append(MongoStatsReader statsReader);

    static Document filter(Sort<MongoStatsReader>[] filters) {
        MongoStatsReader statsReader = new MongoStatsReader(new Document());
        Stream.of(filters).forEach(mongoFilter -> mongoFilter.append(statsReader));
        return statsReader.source();
    }
}
