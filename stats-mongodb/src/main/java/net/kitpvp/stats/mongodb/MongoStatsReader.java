package net.kitpvp.stats.mongodb;

import lombok.Getter;
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
public class MongoStatsReader implements StatsReader {

    private final Document source;

    public final Document source() {
        return this.source;
    }

    @Override
    public <V> V find(String key, V def) {
        return this.find(key, this.source, def);
    }

    private <T> T find(String key, Document document, T def) {
        if(!key.contains(".")){
            try{
                T result = (T) document.get(key);

                return result != null ? result : def;
            }catch(Throwable t){
                return def;
            }
        }else{
            String entry = key.substring(0, key.indexOf("."));
            String path = key.substring(entry.length() + 1);
            if(!document.containsKey(entry)){
                return def;
            }
            Document sub = document.get(entry, Document.class);

            return find(path, sub, def);
        }
    }

    @Override
    public <K, V, U> Set<U> getStatKeys(StatsKey<K, V> statsKey, K k, Function<String, U> function) {
        String key = statsKey.key(k);
        Map<String, Object> map;
        if(key.contains("."))
            map = this.find(key.substring(0, key.lastIndexOf('.')), new HashMap<>());
        else
            map = this.source;

        return map.keySet().stream().map(function).filter(Objects::nonNull).collect(Collectors.toSet());
    }
}
