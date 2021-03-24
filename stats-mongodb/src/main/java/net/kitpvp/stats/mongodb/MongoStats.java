package net.kitpvp.stats.mongodb;

import lombok.Getter;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.mongodb.query.MongoCountQuery;
import net.kitpvp.stats.mongodb.query.MongoFindQuery;
import net.kitpvp.stats.mongodb.query.MongoWriteQuery;

import java.util.UUID;

import static net.kitpvp.stats.mongodb.model.Filters.eq;

public class MongoStats implements Stats<Database> {

    @Getter
    private final UUID playerId;
    @Getter
    private final Collection collection;

    public MongoStats(UUID playerId, Collection collection) {
        this.playerId = playerId;
        this.collection = collection;
    }

    public final MongoFindQuery find(Database database) {
        return Mongo.find(database, this.collection, eq(this.playerId));
    }

    public final MongoCountQuery count(Database database) {
        return Mongo.count(database, this.collection, eq(this.playerId));
    }

    public final MongoWriteQuery write(Database database) {
        return Mongo.write(database, this.collection, eq(this.playerId));
    }

    @Override
    public StatsReader load(Database database) {
        StatsReader statsReader = this.find(database).first();
        if(statsReader == null)
            statsReader = new MongoStatsReader();
        return statsReader;
    }
}
