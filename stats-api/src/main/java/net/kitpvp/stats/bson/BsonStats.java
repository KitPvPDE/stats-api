package net.kitpvp.stats.bson;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import org.bson.Document;

import java.util.Objects;

@RequiredArgsConstructor
public class BsonStats extends BsonStatsReader implements StatsReader, StatsWriter {

    public BsonStats(Document database) {
        super(database);
    }

    public void bson(Document source) {
        this.bson().putAll(source);
    }

    @Override
    public <T> void write(String key, T value) {
        BsonUtils.setValue(key, this.bson(), value);
    }

    @Override
    public <V> V find(String key, V def) {
        return BsonUtils.getValue(key, this.bson(), def);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BsonStats bsonStats = (BsonStats) o;
        return this.bson().equals(bsonStats.bson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.bson());
    }
}
