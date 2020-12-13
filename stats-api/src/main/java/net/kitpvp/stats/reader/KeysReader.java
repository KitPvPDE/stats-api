package net.kitpvp.stats.reader;

import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface KeysReader extends Reader {

    default <K, V, U> Set<U> getStatKeys(StatsKey<K, V> statsKey, Function<String, U> function) {
        String prefix = statsKey.keyFunction().prefix();

        System.out.println(prefix);
        Document map;
        if(prefix != null && !prefix.isEmpty()) {
            System.out.println("a");
            System.err.println("a");
            System.out.println("find() for " + prefix + " in " + this.bson());
            map = this.find(prefix, new Document());
            System.out.println("=" + map);
        } else {
            System.err.println("b");
            System.out.println("b");
            map = this.bson();
        }


        return map.keySet().stream().map(function).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    default <K, V, U> Set<U> getStatKeys(StatsKey<K, V> statsKey, K k, Function<String, U> function) {
        return this.getStatKeys(statsKey, function);
    }

    default <K, V> Set<String> getStatKeys(StatsKey<K, V> statsKey) {
        return this.getStatKeys(statsKey, Function.identity());
    }

    default <K, V> Set<String> getStatKeys(StatsKey<K, V> statsKey, K k) {
        return this.getStatKeys(statsKey, Function.identity());
    }
}
