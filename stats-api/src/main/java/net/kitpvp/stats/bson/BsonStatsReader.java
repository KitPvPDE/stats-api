package net.kitpvp.stats.bson;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BsonStatsReader implements StatsReader {

    public static final StatsKey<String, Document> DOCUMENT_KEY = StatsKey.<String, Document>builder().
            keyBuilder(builder -> builder.function(Function.identity())).defaultValue(Document::new).build();
    public static final Gson GSON = new Gson();

    private final Document database;

    public BsonStatsReader() {
        this(new Document());
    }

    @Override
    public Document bson() {
        return this.database;
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

    public final <T> T mapFromJson(Gson gson, Class<T> type) {
        return gson.fromJson(this.database.toJson(), type);
    }

    public final <T> T mapFromJson(Class<T> type) {
        return this.mapFromJson(GSON, type);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        BsonStatsReader that = (BsonStatsReader) o;
        return database.equals(that.database);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database);
    }
}
