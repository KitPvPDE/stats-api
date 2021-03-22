package net.kitpvp.stats.mongodb;

import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.mongodb.query.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;

public final class Mongo {

    public static MongoBulkQuery bulk(@NotNull Database database, @NotNull Collection collection) {
        return new MongoBulkQuery(database, collection);
    }

    public static MongoFindQuery find(@NotNull Database database, @NotNull Collection collection) {
        return new MongoFindQuery(database, collection);
    }

    public static MongoFindQuery find(@NotNull Database database, @NotNull Collection collection, @NotNull Bson filter) {
        return new MongoFindQuery(database, collection).filter(filter);
    }

    public static MongoFindQuery find(@NotNull Database database, @NotNull Collection collection, @NotNull Bson... filters) {
        return new MongoFindQuery(database, collection).filters(filters);
    }

    public static MongoWriteQuery write(@NotNull Database database, @NotNull Collection collection) {
        return new MongoWriteQuery(database, collection);
    }

    public static MongoWriteQuery write(@NotNull Database database, @NotNull Collection collection, @NotNull Bson filter) {
        return new MongoWriteQuery(database, collection).filter(filter);
    }

    public static MongoWriteQuery write(@NotNull Database database, @NotNull Collection collection, @NotNull Bson... filters) {
        return new MongoWriteQuery(database, collection).filters(filters);
    }

    public static MongoCountQuery count(@NotNull Database database, @NotNull Collection collection) {
        return new MongoCountQuery(database, collection);
    }

    public static MongoCountQuery count(@NotNull Database database, @NotNull Collection collection, @NotNull Bson filter) {
        return new MongoCountQuery(database, collection).filter(filter);
    }

    public static MongoCountQuery count(@NotNull Database database, @NotNull Collection collection, @NotNull Bson... filters) {
        return new MongoCountQuery(database, collection).filters(filters);
    }

    public static MongoDeleteQuery delete(@NotNull Database database, @NotNull Collection collection) {
        return new MongoDeleteQuery(database, collection);
    }

    public static MongoDeleteQuery delete(@NotNull Database database, @NotNull Collection collection, @NotNull Bson filter) {
        return new MongoDeleteQuery(database, collection).filter(filter);
    }

    public static MongoDeleteQuery delete(@NotNull Database database, @NotNull Collection collection, @NotNull Bson... filters) {
        return new MongoDeleteQuery(database, collection).filters(filters);
    }

    public static MongoInsertQuery insert(@NotNull Database database, @NotNull Collection collection) {
        return new MongoInsertQuery(database, collection);
    }

    public static MongoInsertQuery insert(@NotNull Database database, @NotNull Collection collection, @NotNull Document... inserts) {
        return new MongoInsertQuery(database, collection).insert(inserts);
    }

    public static StatsReader findOne(@NotNull Database database, @NotNull Collection collection, @NotNull Bson filter) {
        return find(database, collection, filter).first();
    }
}
