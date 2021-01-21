package net.kitpvp.stats.mongodb.query;

import com.google.common.base.Preconditions;
import net.kitpvp.mongodbapi.database.Collection;
import net.kitpvp.mongodbapi.database.Database;
import net.kitpvp.stats.Stats;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.mongodb.api.async.AsyncExecutable;
import net.kitpvp.stats.query.InsertQuery;
import net.kitpvp.stats.query.insert.Insert;

import java.util.stream.Stream;

public final class MongoInsertQuery implements InsertQuery<BsonStatsWriter>, AsyncExecutable {

    private final Database database;
    private final Collection collection;
    private final BsonStatsWriter document;

    public MongoInsertQuery(Database database, Collection collection) {
        this.database = database;
        this.collection = collection;
        this.document = new BsonStatsWriter();
    }

    @Override
    @SafeVarargs
    public final MongoInsertQuery insert(Insert<BsonStatsWriter>... inserts) {
        Preconditions.checkArgument(inserts.length > 0, "Zero inserts specified");
        Stream.of(inserts).forEach(insert -> insert.append(this.document));
        return this;
    }

    public final void execute() {
        Stats.checkForMainThread();

        this.database.getCollection(this.collection).insertOne(this.document.bson());
    }
}
