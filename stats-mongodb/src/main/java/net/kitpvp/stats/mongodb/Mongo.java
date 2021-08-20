package net.kitpvp.stats.mongodb;

import lombok.Getter;
import lombok.Setter;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.mongodb.connection.MongoDBCollection;
import net.kitpvp.stats.mongodb.query.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.NotNull;

public final class Mongo {

    @Getter
    @Setter
    private static MongoUuidRepresentation uuidRepresentation = MongoUuidRepresentation.STRING;

    public static MongoBulkQuery bulk(@NotNull MongoDBCollection collection) {
        return new MongoBulkQuery(collection);
    }

    public static MongoFindQuery find(@NotNull MongoDBCollection collection) {
        return new MongoFindQuery(collection);
    }

    public static MongoFindQuery find(@NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return new MongoFindQuery(collection).filter(filter);
    }

    public static MongoFindQuery find(@NotNull MongoDBCollection collection, @NotNull Bson... filters) {
        return new MongoFindQuery(collection).filters(filters);
    }

    public static MongoWriteQuery write(@NotNull MongoDBCollection collection) {
        return new MongoWriteQuery(collection);
    }

    public static MongoWriteQuery write(@NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return new MongoWriteQuery(collection).filter(filter);
    }

    public static MongoWriteQuery write(@NotNull MongoDBCollection collection, @NotNull Bson... filters) {
        return new MongoWriteQuery(collection).filters(filters);
    }

    public static MongoCountQuery count(@NotNull MongoDBCollection collection) {
        return new MongoCountQuery(collection);
    }

    public static MongoCountQuery count(@NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return new MongoCountQuery(collection).filter(filter);
    }

    public static MongoCountQuery count(@NotNull MongoDBCollection collection, @NotNull Bson... filters) {
        return new MongoCountQuery(collection).filters(filters);
    }

    public static MongoDeleteQuery delete(@NotNull MongoDBCollection collection) {
        return new MongoDeleteQuery(collection);
    }

    public static MongoDeleteQuery delete(@NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return new MongoDeleteQuery(collection).filter(filter);
    }

    public static MongoDeleteQuery delete(@NotNull MongoDBCollection collection, @NotNull Bson... filters) {
        return new MongoDeleteQuery(collection).filters(filters);
    }

    public static MongoInsertQuery insert(@NotNull MongoDBCollection collection) {
        return new MongoInsertQuery(collection);
    }

    public static MongoInsertQuery insert(@NotNull MongoDBCollection collection, @NotNull Document... inserts) {
        return new MongoInsertQuery(collection).insert(inserts);
    }

    public static StatsReader findOne(@NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return find(collection, filter).first();
    }

    /*
     * Deprecated methods
     */

    @Deprecated
    public static MongoBulkQuery bulk(Object ignored, @NotNull MongoDBCollection collection) {
        return bulk(collection);
    }

    @Deprecated
    public static MongoFindQuery find(Object ignored, @NotNull MongoDBCollection collection) {
        return find(collection);
    }
    @Deprecated
    public static MongoFindQuery find(Object ignored, @NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return find(collection, filter);
    }

    @Deprecated
    public static MongoFindQuery find(Object ignored, @NotNull MongoDBCollection collection, @NotNull Bson... filters) {
        return find(collection, filters);
    }

    @Deprecated
    public static MongoWriteQuery write(Object ignored, @NotNull MongoDBCollection collection) {
        return write(collection);
    }

    @Deprecated
    public static MongoWriteQuery write(Object ignored, @NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return write(collection, filter);
    }

    @Deprecated
    public static MongoWriteQuery write(Object ignored, @NotNull MongoDBCollection collection, @NotNull Bson... filters) {
        return write(collection, filters);
    }

    @Deprecated
    public static MongoCountQuery count(Object ignored, @NotNull MongoDBCollection collection) {
        return count(collection);
    }

    @Deprecated
    public static MongoCountQuery count(Object ignored, @NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return count(collection, filter);
    }

    @Deprecated
    public static MongoCountQuery count(Object ignored, @NotNull MongoDBCollection collection, @NotNull Bson... filters) {
        return count(collection, filters);
    }

    @Deprecated
    public static MongoDeleteQuery delete(Object ignored, @NotNull MongoDBCollection collection) {
        return delete(collection);
    }

    @Deprecated
    public static MongoDeleteQuery delete(Object ignored, @NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return delete(collection, filter);
    }

    @Deprecated
    public static MongoDeleteQuery delete(Object ignored, @NotNull MongoDBCollection collection, @NotNull Bson... filters) {
        return delete(collection, filters);
    }

    @Deprecated
    public static MongoInsertQuery insert(Object ignored, @NotNull MongoDBCollection collection) {
        return insert(collection);
    }

    @Deprecated
    public static MongoInsertQuery insert(Object ignored, @NotNull MongoDBCollection collection, @NotNull Document... inserts) {
        return insert(collection, inserts);
    }

    @Deprecated
    public static StatsReader findOne(Object ignored, @NotNull MongoDBCollection collection, @NotNull Bson filter) {
        return findOne(collection, filter);
    }
}
