package net.kitpvp.stats;

import net.kitpvp.stats.season.Season;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSeasons {

    @Test
    public void testSeason() {
        for(Season season : Season.getSeasons()) {
            assertTrue(season.getNumber() > 0);
        }
    }

}
