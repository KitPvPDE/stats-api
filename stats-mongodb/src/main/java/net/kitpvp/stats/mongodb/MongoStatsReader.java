package net.kitpvp.stats.mongodb;

import net.kitpvp.stats.bson.BsonStatsReader;
import org.bson.Document;

public class MongoStatsReader extends BsonStatsReader {

    public MongoStatsReader() {
        super(new Document());
    }

    public MongoStatsReader(Document document) {
        super(document);
    }

    public final Document source() {
        return this.bson();
    }
}
