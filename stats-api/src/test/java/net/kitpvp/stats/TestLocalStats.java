package net.kitpvp.stats;

import net.kitpvp.stats.keys.StatsKey;
import net.kitpvp.stats.bson.BsonUtils;
import net.kitpvp.stats.local.LocalStats;
import org.bson.Document;
import org.junit.Test;

import java.util.UUID;
import java.util.function.Function;

import static org.junit.Assert.*;

public class TestLocalStats {

    @Test
    public void testStats() {
        UUID randomId = UUID.randomUUID();
        LocalStats localStats = new LocalStats(randomId);

        assertEquals(randomId, localStats.getPlayerId());

        StatsKey<String, String> key = StatsKey.<String, String>builder().
                keyBuilder(builder -> builder.prefix("prefix").function(Function.identity()).inverse(Function.identity()).suffix("suffix")).
                defaultValue("default").build();

        assertEquals("default", localStats.getStatKey(key, "test"));
        localStats.setStatKey(key, "test", "value");
        assertEquals("value", localStats.getStatKey(key, "test"));
        localStats.unsetStatKey(key, "test", "value");
        assertEquals("default", localStats.getStatKey(key, "test"));
    }

    @Test
    public void testUpdateStats() {
        LocalStats localStats = new LocalStats(UUID.randomUUID());

        Document document = new Document("test", new Document("test2", 4124));
        Document updated = new Document("test", new Document("test2", 0x0f0));

        localStats.update(document);

        assertEquals(document, localStats.source());
        localStats.update(updated);
        assertEquals(updated, localStats.source());
    }

    @Test
    public void testPath() {
        assertArrayEquals(new String[] {"prefix", "test", "suffix"}, BsonUtils.PATH_SPLIT.split("prefix.test.suffix", -1));
    }
}
