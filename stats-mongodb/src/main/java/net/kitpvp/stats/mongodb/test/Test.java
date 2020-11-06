package net.kitpvp.stats.mongodb.test;

import com.mongodb.client.MongoCollection;
import net.kitpvp.mongodbapi.MongoConnector;
import net.kitpvp.mongodbapi.async.distribution.Distribution;
import net.kitpvp.mongodbapi.database.PluginDatabase;
import net.kitpvp.stats.mongodb.MongoStats;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Test {

    public void test() {
        PluginDatabase database = new PluginDatabase() {
            @Override
            public MongoCollection<Document> getCollection(@NotNull String s, @NotNull String s1) {
                throw new UnsupportedOperationException("TODO");
            }
        };
        UUID playerId = UUID.randomUUID();
        MongoStats stats = new MongoStats(playerId, net.kitpvp.mongodbapi.MongoCollection.STATS);
        //stats.find(database).
    }

}
