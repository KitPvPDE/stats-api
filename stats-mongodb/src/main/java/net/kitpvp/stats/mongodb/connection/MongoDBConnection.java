package net.kitpvp.stats.mongodb.connection;

public interface MongoDBConnection {

    MongoDBCollection getMongoCollection(String database, String name);
}
