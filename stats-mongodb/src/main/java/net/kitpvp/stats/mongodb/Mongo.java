package net.kitpvp.stats.mongodb;

import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.mongodb.query.*;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import net.kitpvp.stats.mongodb.query.insert.MongoInsert;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class Mongo {

    public static MongoFindQuery find(@NotNull Database database, @NotNull Collection collection) {
        return new MongoFindQuery(database, collection);
    }

    public static MongoFindQuery find(@NotNull Database database, @NotNull Collection collection, @NotNull MongoFilter... filters) {
        return new MongoFindQuery(database, collection).filter(filters);
    }

    public static MongoWriteQuery write(@NotNull Database database, @NotNull Collection collection) {
        return new MongoWriteQuery(database, collection);
    }

    public static MongoWriteQuery write(@NotNull Database database, @NotNull Collection collection, @NotNull MongoFilter... filters) {
        return new MongoWriteQuery(database, collection).filter(filters);
    }

    public static MongoWriteQuery write(@NotNull Database database, @NotNull Collection collection, @Nullable Consumer<MongoStatsReader> callback) {
        return new MongoWriteQuery(database, collection, callback);
    }

    public static MongoWriteQuery write(@NotNull Database database, @NotNull Collection collection, @Nullable Consumer<MongoStatsReader> callback, @NotNull MongoFilter... filters) {
        return new MongoWriteQuery(database, collection, callback).filter(filters);
    }

    public static MongoCountQuery count(@NotNull Database database, @NotNull Collection collection) {
        return new MongoCountQuery(database, collection);
    }

    public static MongoCountQuery count(@NotNull Database database, @NotNull Collection collection, @NotNull MongoFilter... filters) {
        return new MongoCountQuery(database, collection).filter(filters);
    }

    public static MongoDeleteQuery delete(@NotNull Database database, @NotNull Collection collection) {
        return new MongoDeleteQuery(database, collection);
    }

    public static MongoDeleteQuery delete(@NotNull Database database, @NotNull Collection collection, @NotNull MongoFilter... filters) {
        return new MongoDeleteQuery(database, collection).filter(filters);
    }

    public static MongoInsertQuery insert(@NotNull Database database, @NotNull Collection collection) {
        return new MongoInsertQuery(database, collection);
    }

    public static MongoInsertQuery insert(@NotNull Database database, @NotNull Collection collection, @NotNull MongoInsert... inserts) {
        return new MongoInsertQuery(database, collection).insert(inserts);
    }

    public static MongoStatsReader findOne(@NotNull Database database, @NotNull Collection collection, @NotNull MongoFilter filter) {
        return find(database, collection, filter).first();
    }
}
