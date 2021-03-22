package net.kitpvp.stats.bson;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.bson.Document;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "database")
public class BsonStatsReader implements BsonReader {

    private final Document database;

    public BsonStatsReader() {
        this(new Document());
    }

    public Document bson() {
        return this.database;
    }

    @Override
    public String toString() {
        return "BsonStatsReader{" +
                "database=" + (database != null ? database.toJson() : null) +
                '}';
    }
}
