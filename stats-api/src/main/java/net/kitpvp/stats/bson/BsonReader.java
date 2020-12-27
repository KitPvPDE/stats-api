package net.kitpvp.stats.bson;

import net.kitpvp.stats.StatsReader;
import org.bson.Document;

public interface BsonReader extends StatsReader {

    Document bson();

    @Override
    default <V> V find(String key, V def) {
        return BsonUtils.getValue(key, this.bson(), def);
    }
}
