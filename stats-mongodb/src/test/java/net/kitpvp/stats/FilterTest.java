package net.kitpvp.stats;

import net.kitpvp.stats.mongodb.query.filter.MongoFilter;
import net.kitpvp.stats.mongodb.query.filter.filters.MongoFilters;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class FilterTest {

    @Test
    public void testUUID() {
        UUID uuid = UUID.randomUUID();
        MongoFilter filter = MongoFilters.uuid(uuid);
        assertThat(filter.filter().get("_id"), is(uuid.toString()));
    }

    @Test
    public void testUUIDWithField() {
        UUID uuid = UUID.randomUUID();
        String field = "test";
        MongoFilter filter = MongoFilters.uuid(uuid, field);
        assertThat(filter.filter().get(field), is(uuid.toString()));
    }

}
