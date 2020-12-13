package net.kitpvp.stats.bson;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;

import java.util.Objects;
import java.util.function.Function;

@RequiredArgsConstructor
public class BsonStats implements StatsReader, StatsWriter {

    public static final StatsKey<String, Document> DOCUMENT_KEY = StatsKey.<String, Document>builder().
            keyBuilder(builder -> builder.function(Function.identity())).defaultValue(Document::new).build();

    private final Document database;

    @Override
    public Document bson() {
        return this.database;
    }

    public void bson(Document source) {
        this.database.putAll(source);
    }

    @Override
    public <T> void write(String key, T value) {
        BsonUtils.setValue(key, this.database, value);
    }

    @Override
    public <V> V find(String key, V def) {
        return BsonUtils.getValue(key, this.database, def);
    }

    @Override
    public <K, V> StatsReader mapStatsReader(StatsKey<K, V> statsKey, K key) {
        String path = statsKey.key(key);
        Document document = this.getStatKey(DOCUMENT_KEY, path);
        return new BsonStatsReader(document);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BsonStats bsonStats = (BsonStats) o;
        return database.equals(bsonStats.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database);
    }
}
