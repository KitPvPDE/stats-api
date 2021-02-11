package net.kitpvp.stats;

import net.kitpvp.stats.bson.BsonStats;
import net.kitpvp.stats.bson.BsonStatsReader;
import net.kitpvp.stats.keys.SStatsKey;
import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class TestBsonReader {

    private final Document database = new Document().
            append("first", new Document("second", new Document("third", "test"))).
            append("second", new Document("third", "test")).
            append("third", new Document());

    @Test
    public void testRead(){
        BsonStatsReader statsReader = new BsonStatsReader(this.database);
        SStatsKey<String> key = SStatsKey.<String>builder().defaultValue("").keyBuilder(builder -> builder.path("first.second.third")).build();

        assertEquals(statsReader.getStatKey(key), "test");
    }

    @Test
    public void testDefault() {
        BsonStatsReader statsReader = new BsonStatsReader(new Document());
        SStatsKey<String> key = SStatsKey.<String>builder().defaultValue("default").keyBuilder(builder -> builder.path("first.second.third")).build();

        assertEquals("default", statsReader.getStatKey(key));
    }

    @Test
    public void testNested() {
        BsonStatsReader statsReader = new BsonStatsReader(database);
        StatsKey<String, Object> statsKey = StatsKey.<String, Object>builder().keyBuilder(builder -> builder.function(Function.identity()).
                inverse(Function.identity())).defaultValue((Object) null).build();

        for(String key : statsReader.getKeys(statsKey)) {
            assertEquals(new BsonStatsReader(database.get(key, Document.class)), statsReader.getStatReader(statsKey, key).orElse(null));
        }
    }

    @Test
    public void testHas() {
        BsonStats statsReader = new BsonStats(database);

        StatsKey<String, Object> statsKey = StatsKey.<String, Object>builder().keyBuilder(builder ->
                builder.prefix("prefix").function(Function.identity()).inverse(Function.identity()).suffix("suffix")).build();

        assertFalse(statsReader.hasStatKey(statsKey, "test"));
        statsReader.setStatKey(statsKey, "test", new Object());
        assertTrue(statsReader.hasStatKey(statsKey, "test"));
        assertFalse(statsReader.hasStatKey(statsKey, "other"));
    }
}
