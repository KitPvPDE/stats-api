package net.kitpvp.stats.mongodb.query;

import com.mongodb.Function;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import net.kitpvp.stats.query.FindQuery;
import net.kitpvp.stats.query.filter.Filter;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class MongoFindQuery implements FindQuery<MongoStatsReader> {

    private final Database database;
    private final Function<Document, MongoStatsReader> mapper = MongoStatsReader::new;
    private final FindIterable<Document> iterable;

    public MongoFindQuery(Database database, Collection collection) {
        this.database = database;
        this.iterable = this.database.getCollection(collection).find();
    }

    public MongoFindQuery(Database database, Collection collection, MongoFilter... filters) {
        this.database = database;
        this.iterable = this.database.getCollection(collection).find().filter(MongoFilter.filter(filters));
    }

    @Override
    public final @NotNull MongoFindQuery limit(int limit) {
        this.iterable.limit(limit);
        return this;
    }

    @Override
    public final @NotNull MongoFindQuery skip(int skip) {
        this.iterable.skip(skip);
        return this;
    }

    @Override
    @SafeVarargs
    public final @NotNull MongoFindQuery sort(Filter<MongoStatsReader>... filters) {
        this.iterable.sort(MongoFilter.filter(filters));
        return this;
    }

    public final @NotNull MongoIterable<MongoStatsReader> find() {
        return this.iterable.map(this.mapper);
    }

    public final @Nullable MongoStatsReader first() {
        MongoIterable<MongoStatsReader> iterable = this.iterable.map(this.mapper);

        return iterable.first();
    }

    @Override
    public final @NotNull MongoCursor<MongoStatsReader> iterator() {
        MongoIterable<MongoStatsReader> iterable = this.find();

        return iterable.cursor();
    }
}
