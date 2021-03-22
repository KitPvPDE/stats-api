package net.kitpvp.stats;

import net.kitpvp.stats.bson.BsonStatsReader;
import org.bson.Document;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ReaderTest {

    @Test
    public void testKeys() {
        Document database = new Document("prefix",
                    new Document("first", new Document("suffix", 21))
                            .append("second", new Document("suffix", -420))
                            .append("third", new Document("suffix", 0)));
        StatsReader statsReader = new BsonStatsReader(database);
        Key<String> key = Key.<String>builder()
                .prefix("prefix")
                .function(Key.identity())
                .suffix("suffix")
                .buildKey();
        Set<String> keys = new HashSet<>(Arrays.asList("first", "second", "third"));
        assertEquals(keys, statsReader.getKeys(key));
    }
}
