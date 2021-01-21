package net.kitpvp.stats.bson;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bson.Document;

@RequiredArgsConstructor
@ToString(of = "database")
@EqualsAndHashCode(of = "database")
public class BsonStatsWriter implements BsonWriter<BsonStatsWriter> {

    private final Document database;

    public BsonStatsWriter() {
        this(new Document());
    }

    public Document bson() {
        return this.database;
    }

    @Override
    public BsonStatsWriter writer() {
        return this;
    }
}
