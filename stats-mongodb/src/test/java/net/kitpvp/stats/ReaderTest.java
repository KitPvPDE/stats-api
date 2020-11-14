package net.kitpvp.stats;

import net.kitpvp.stats.keys.numeric.IntSeasonKey;
import net.kitpvp.stats.keys.numeric.IntStageKey;
import net.kitpvp.stats.keys.numeric.IntStatsKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.update.updates.MongoUpdates;
import net.kitpvp.stats.season.Season;
import org.bson.Document;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class ReaderTest {

    private final IntStatsKey<String> intKey = IntStatsKey.<String>builder().
            keyBuilder((builder) -> builder.prefix("test").function(Function.identity()).suffix("test3")).
            build();
    private final IntSeasonKey<String> intSeasonKey = IntStatsKey.<String>builder().
            keyBuilder((builder) -> builder.prefix("prefix").function(Function.identity()).suffix("suffix")).
            season();
    private final IntStageKey<String> intStageKey = IntStatsKey.<String>builder().
            keyBuilder((builder) -> builder.prefix("prefix").function(Function.identity()).suffix("suffix")).
            stage();

    @Test
    public void testKeyUpdate() {
        MongoStatsReader statsReader = new MongoStatsReader(new Document());
        MongoUpdates.inc(intKey, "test2", 3).append(statsReader);

        assertEquals(statsReader.source(), new Document("$inc", new Document("test.test2.test3", 3)));
    }

    @Test
    public void testSeasonUpdate() {
        MongoStatsReader statsReader = new MongoStatsReader(new Document());
        MongoUpdates.inc(intSeasonKey, "function", 3).append(statsReader);

        assertEquals(statsReader.source(), new Document("$inc", new Document().
                append("seasons.season" + Season.getSeason() + ".prefix.function.suffix", 3).
                append("alltime.prefix.function.suffix", 3)));
    }

    @Test
    public void testStageUpdate() {
        MongoStatsReader statsReader = new MongoStatsReader(new Document());
        MongoUpdates.inc(intStageKey, "function", 3).append(statsReader);

        assertEquals(statsReader.source(), new Document("$inc", new Document().
                append("seasons.season" + Season.getSeason() + ".stages.stage1.prefix.function.suffix", 3).
                append("seasons.season" + Season.getSeason() + ".prefix.function.suffix", 3).
                append("alltime.prefix.function.suffix", 3)));
    }
}
