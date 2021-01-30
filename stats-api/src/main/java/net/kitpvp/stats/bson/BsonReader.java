package net.kitpvp.stats.bson;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.api.keys.Entry;
import net.kitpvp.stats.api.keys.Key;
import net.kitpvp.stats.converter.Decoder;
import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;

import java.util.*;
import java.util.stream.Collectors;

import static net.kitpvp.stats.api.keys.Entry.entry;

public interface BsonReader extends StatsReader {

    Document bson();

    @Override
    default <V> V find(String key, V def) {
        return BsonUtils.getValue(key, this.bson(), def);
    }

    @Override
    default <K> Set<K> getKeys(Key<K> statsKey) {
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
    default <K> StatsReader getStatReader(Key<K> statsKey, K key) {
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
    default <K> Set<Entry<K, StatsReader>> getStatEntries(Key<K> statsKey) {
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
    default <K> Set<StatsReader> getStatReaders(Key<K> statsKey) {
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
    default <K> List<StatsReader> getStatKeys(Key<K> statsKey, K key) {
        return this.find(statsKey.key(key), new ArrayList<Document>()).stream().map(BsonStatsReader::new).collect(Collectors.toList());
    }
}
