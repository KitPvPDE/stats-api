package net.kitpvp.stats.mongodb;

import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.mongodb.query.MongoCountQuery;
import net.kitpvp.stats.mongodb.query.MongoFindQuery;
import net.kitpvp.stats.mongodb.query.MongoWriteQuery;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import org.jetbrains.annotations.NotNull;

public class Mongo {

    public static MongoFindQuery find(@NotNull Database database, @NotNull Collection collection) {
        return new MongoFindQuery(database, collection);
    }

    public static MongoFindQuery find(@NotNull Database database, @NotNull Collection collection, @NotNull MongoFilter... filters) {
        return new MongoFindQuery(database, collection, filters);
    }

    public static MongoWriteQuery write(@NotNull Database database, @NotNull Collection collection) {
        return new MongoWriteQuery(database, collection);
    }

    public static MongoWriteQuery write(@NotNull Database database, @NotNull Collection collection, @NotNull MongoFilter... filters) {
        return new MongoWriteQuery(database, collection).filter(filters);
    }

    public static MongoCountQuery count(@NotNull Database database, @NotNull Collection collection) {
        return new MongoCountQuery(database, collection);
    }

    public static MongoCountQuery count(@NotNull Database database, @NotNull Collection collection, @NotNull MongoFilter... filters) {
        return new MongoCountQuery(database, collection).filter(filters);
    }
}