package net.kitpvp.stats.bson;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsWriter;
import org.bson.Document;

import java.util.Objects;

@RequiredArgsConstructor
public class BsonStatsWriter implements BsonWriter {

    private final Document database;

    public BsonStatsWriter() {
        this(new Document());
    }

    public Document bson() {
        return this.database;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BsonStatsWriter that = (BsonStatsWriter) o;
        return database.equals(that.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database);
    }

    @Override
    public String toString() {
        return "BsonStatsWriter{" +
                "database=" + database +
                '}';
    }
}
