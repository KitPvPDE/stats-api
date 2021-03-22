package net.kitpvp.stats;

import net.kitpvp.stats.mongodb.Mongo;
import net.kitpvp.stats.mongodb.MongoUuidRepresentation;
import net.kitpvp.stats.mongodb.model.Filters;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class FilterTest {

    @Test
    public void testUUID() {
        Mongo.setUuidRepresentation(MongoUuidRepresentation.UUID);

        UUID uuid = UUID.randomUUID();
        assertEquals(com.mongodb.client.model.Filters.eq(uuid), Filters.eq(uuid));

        String fieldName = "fieldName";
        assertEquals(com.mongodb.client.model.Filters.eq(fieldName, uuid), Filters.eq(Key.identity(), fieldName, uuid));
    }
}
