package net.kitpvp.stats;

import net.kitpvp.stats.keys.inc.numbers.LongSStageKey;
import net.kitpvp.stats.mongodb.MongoStatsReader;
import net.kitpvp.stats.mongodb.query.update.MongoUpdate;
import net.kitpvp.stats.mongodb.query.update.updates.MongoUpdates;
import org.bson.Document;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReaderTest {

    private final MongoStatsReader statsReader = new MongoStatsReader(new Document());
    private final LongSStageKey longKey =

    @Test
    public void testUpdate() {
        MongoUpdate update = MongoUpdates.inc(longKey, null, )
    }
}
