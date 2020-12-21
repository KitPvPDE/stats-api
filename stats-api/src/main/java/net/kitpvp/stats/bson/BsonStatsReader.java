package net.kitpvp.stats.bson;

import lombok.RequiredArgsConstructor;
import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.StatsWriter;
import net.kitpvp.stats.api.keys.Entry;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.bson.codec.BsonDecoder;
import net.kitpvp.stats.converter.Converter;
import net.kitpvp.stats.converter.Decoder;
import org.bson.Document;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static net.kitpvp.stats.api.keys.Entry.entry;

@RequiredArgsConstructor
public class BsonStatsReader implements StatsReader {

    private final Document database;

    public BsonStatsReader() {
        this(new Document());
    }

    public Document bson() {
        return this.database;
    }

    @Override
    public <V> V find(String key, V def) {
        return BsonUtils.getValue(key, this.database, def);
    }

    @Override
    public <K> Set<K> getKeys(Key<K> statsKey) {
        String prefix = statsKey.keyFunction().prefix();

        Document map;
        if(prefix != null && !prefix.isEmpty()) {
            map = this.find(prefix, new Document());
        } else {
            map = this.bson();
        }
        return map.keySet().stream().map(statsKey.keyFunction().inverse()).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @Override
    public <K> StatsReader getStatReader(Key<K> statsKey, K key) {
        String path = statsKey.keyFunction().key(key);

        Document map;
        if(path != null && !path.isEmpty()) {
            map = this.find(path, new Document());
        } else {
            map = this.bson();
        }
        return new BsonStatsReader(map);
    }

    @Override
    public <K> Set<Entry<K, StatsReader>> getStatEntries(Key<K> statsKey) {
        String prefix = statsKey.keyFunction().prefix();

        Document map;
        if(prefix != null && !prefix.isEmpty()) {
            map = this.find(prefix, new Document());
        } else {
            map = this.bson();
        }
        return map.entrySet().stream().filter(entry -> entry.getValue() instanceof Document).
                map(entry -> entry(statsKey.keyFunction().inverse(entry.getKey()), (StatsReader) new BsonStatsReader((Document) entry.getValue()))).
                collect(Collectors.toSet());
    }

    @Override
    public <K> Set<StatsReader> getStatReaders(Key<K> statsKey) {
        String prefix = statsKey.keyFunction().prefix();

        Document map;
        if(prefix != null && !prefix.isEmpty()) {
            map = this.find(prefix, new Document());
        } else {
            map = this.bson();
        }
        return map.values().stream().filter(value -> value instanceof Document).
                map(value -> new BsonStatsReader((Document) value)).collect(Collectors.toSet());
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
