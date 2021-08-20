package net.kitpvp.stats.mongodb;

import lombok.Getter;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.query.MongoCountQuery;
import net.kitpvp.stats.mongodb.query.MongoFindQuery;
import net.kitpvp.stats.mongodb.query.MongoWriteQuery;

import java.util.UUID;

import static net.kitpvp.stats.mongodb.model.Filters.eq;

public class MongoStats implements Stats {

    @Getter
    private final UUID playerId;
    @Getter
    private final MongoDBCollection collection;

    public MongoStats(UUID playerId, MongoDBCollection collection) {
        this.playerId = playerId;
        this.collection = collection;
    }

    public final MongoFindQuery find() {
        return Mongo.find(this.collection, eq(this.playerId));
    }

    public final MongoCountQuery count() {
        return Mongo.count(this.collection, eq(this.playerId));
    }

    public final MongoWriteQuery write() {
        return Mongo.write(this.collection, eq(this.playerId));
    }

    @Override
    public StatsReader load() {
        StatsReader statsReader = this.find().first();
        if (statsReader == null)
            statsReader = new MongoStatsReader();
        return statsReader;
    }
}
