package net.kitpvp.stats;


import net.kitpvp.stats.mongodb.model.Updates;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUpdates {

    @Test
    public void testSet() {
        int value = 2139;
        assertEquals(com.mongodb.client.model.Updates.set("fieldName", value), Updates.set(Key.identity(), "fieldName", value));
    }

    @Test
    public void testUnset() {
        assertEquals(com.mongodb.client.model.Updates.unset("fieldName"), Updates.unset(Key.identity(), "fieldName"));
    }

}
