package net.kitpvp.stats;

import net.kitpvp.stats.bson.BsonStats;
import net.kitpvp.stats.bson.BsonStatsWriter;
import net.kitpvp.stats.keys.StatsKey;
import org.bson.Document;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

public class TestBsonWriter {

    @Test
    public void testSet() {
        BsonStats writer = new BsonStats(new Document());

        StatsKey<String, Object> statsKey = StatsKey.<String, Object>builder().keyBuilder(builder ->
                builder.prefix("prefix").function(Function.identity()).inverse(Function.identity()).suffix("suffix")).defaultValue((Object) null).build();

        Object object = new Object();
        writer.setStatKey(statsKey, "key", object);
        assertEquals(object, writer.getStatKey(statsKey, "key"));
        assertNotNull(writer.getStatKey(statsKey, "key"));
        assertNull(writer.getStatKey(statsKey, "other"));
    }

}
