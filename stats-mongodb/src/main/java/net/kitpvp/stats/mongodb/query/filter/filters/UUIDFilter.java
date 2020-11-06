package net.kitpvp.stats.mongodb.query.filter.filters;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;

import java.util.UUID;

@RequiredArgsConstructor
public class UUIDFilter implements MongoFilter {

    private final UUID uuid;
    private final String field;

    public UUIDFilter(UUID uuid) {
        this.uuid = uuid;
        this.field = "_id";
    }

    @Override
    public MongoFilter append(MongoStatsReader statsReader) {
        statsReader.source().append(this.field, this.uuid.toString());
        return this;
    }
}
