package net.kitpvp.stats.bson;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import org.bson.Document;

import java.util.Objects;

@RequiredArgsConstructor
@EqualsAndHashCode(of = "database")
public class BsonStats implements BsonReader, BsonWriter<BsonStats> {

    private final Document database;

    public BsonStats() {
        this(new Document());
    }

    @Override
    public BsonStats writer() {
        return this;
    }

    @Override
    public Document bson() {
        return this.database;
    }

    public void bson(Document source) {
        this.bson().putAll(source);
    }
}
