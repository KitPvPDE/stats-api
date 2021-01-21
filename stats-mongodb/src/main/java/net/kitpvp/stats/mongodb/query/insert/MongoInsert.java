package net.kitpvp.stats.mongodb.query.insert;

import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.query.insert.Insert;

public interface MongoInsert extends Insert<BsonStatsWriter> {

    @Override
    MongoInsert append(BsonStatsWriter writer);
}
