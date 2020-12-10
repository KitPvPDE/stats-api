package net.kitpvp.stats.mongodb;

import lombok.Getter;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.mongodb.query.MongoCountQuery;
import net.kitpvp.stats.mongodb.query.MongoFindQuery;
import net.kitpvp.stats.mongodb.query.MongoWriteQuery;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import net.kitpvp.stats.mongodb.query.filter.filters.MongoFilters;
import net.kitpvp.stats.query.WriteQuery;

import java.util.UUID;
import java.util.function.Consumer;

public class MongoStats implements Stats<Database, MongoStatsReader> {

    @Getter
    private final UUID playerId;
    @Getter
    private final Collection collection;
    @Getter
    private final Consumer<MongoStatsReader> callback;

    public MongoStats(UUID playerId, Collection collection) {
        this.playerId = playerId;
        this.collection = collection;
        this.callback = null;
    }

    public MongoStats(UUID playerId, Collection collection, Consumer<MongoStatsReader> callback) {
        this.playerId = playerId;
        this.collection = collection;
        this.callback = callback;
    }

    @Override
    public MongoFindQuery find(Database database) {
        return Mongo.find(database, this.collection, MongoFilters.uuid(this.playerId));
    }

    @Override
    public MongoCountQuery count(Database database) {
        return Mongo.count(database, this.collection, MongoFilters.uuid(this.playerId));
    }

    @Override
    public MongoWriteQuery write(Database database) {
        return Mongo.write(database, this.collection, this.callback, MongoFilters.uuid(this.playerId));
    }

    @Override
    public MongoStatsReader load(Database database) {
        MongoStatsReader statsReader = Stats.super.load(database);
        if(statsReader == null)
            statsReader = new MongoStatsReader();

        if(this.callback != null) {
            this.callback.accept(statsReader);
        }
        return statsReader;
    }
}
