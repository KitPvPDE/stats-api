package net.kitpvp.stats;

import net.kitpvp.stats.keys.numeric.IntSeasonKey;
import net.kitpvp.stats.keys.numeric.IntStageKey;
import net.kitpvp.stats.keys.numeric.IntStatsKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.update.updates.MongoUpdates;
import net.kitpvp.stats.season.Season;
import org.bson.Document;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
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

        assertEquals(new Document("$inc", new Document().
                append("seasons.season" + Season.getSeason() + ".stages.stage" + Season.getStage() + ".prefix.function.suffix", 3).
                append("seasons.season" + Season.getSeason() + ".prefix.function.suffix", 3).
                append("alltime.prefix.function.suffix", 3)), statsReader.source());
    }

    @Test
    public void testKeys() {
        Document document = new Document().
                append("test", new Document().
                        append("first", new Document("test3", 1)).
                        append("second", new Document("test3", 2)).
                        append("third", new Document("test3", 3)));
        MongoStatsReader statsReader = new MongoStatsReader(document);
        Set<String> keys = new HashSet<String>() {{
            add("first");
            add("second");
            add("third");
        }};
        assertEquals(keys, statsReader.getStatKeys(this.intKey));

        for(String key : statsReader.getStatKeys(this.intKey)) {
            assertEquals(document.get("test", Document.class).get(key, Document.class).getInteger("test3").intValue(), statsReader.getIntKey(this.intKey, key));
        }
    }
}
