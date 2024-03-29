package net.kitpvp.stats.bson;

import net.kitpvp.stats.StatsReader;
import net.kitpvp.stats.api.keys.Entry;
import net.kitpvp.stats.Key;
import org.bson.Document;

import java.util.*;
import java.util.stream.Collectors;

import static net.kitpvp.stats.api.keys.Entry.entry;

public interface BsonReader extends StatsReader {

    Document bson();

    @Override
    default <V> V find(String key, V def, Class<V> type) {
        return BsonUtils.getValue(key, this.bson(), def);
    }

    @Override
    default <K> Set<K> getKeys(Key<K> statsKey) {
        String prefix = statsKey.keyFunction().prefix();

        Document map;
        if(prefix != null && !prefix.isEmpty()) {
            map = this.find(prefix, new Document(), Document.class);
        } else {
            map = this.bson();
        }
        return map.keySet().stream().map(statsKey.keyFunction().inverse()).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @Override
    default <K> Optional<StatsReader> getStatReader(Key<K> statsKey, K key) {
        String path = statsKey.keyFunction().key(key);

        Document map;
        if(path != null && !path.isEmpty()) {
            map = this.find(path, null, Document.class);
        } else if(path != null){
            map = this.bson();
        } else {
            return Optional.empty();
        }
        if(map != null) {
            return Optional.of(new BsonStatsReader(map));
        } else {
            return Optional.empty();
        }
    }

    @Override
    default <K> Set<Entry<K, StatsReader>> getStatEntries(Key<K> statsKey) {
        String prefix = statsKey.keyFunction().prefix();

        Document map;
        if(prefix != null && !prefix.isEmpty()) {
            map = this.find(prefix, new Document(), Document.class);
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
            map = this.find(prefix, new Document(), Document.class);
        } else {
            map = this.bson();
        }
        return map.values().stream().filter(value -> value instanceof Document).
                map(value -> new BsonStatsReader((Document) value)).collect(Collectors.toSet());
    }

    @Override
    default <K> List<StatsReader> getStatKeys(Key<K> statsKey, K key) {
        List<Document> documents = this.find(statsKey.key(key), new ArrayList<>(), List.class);
        return documents.stream().map(BsonStatsReader::new).collect(Collectors.toList());
    }
}
