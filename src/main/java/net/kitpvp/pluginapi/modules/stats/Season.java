package net.kitpvp.pluginapi.modules.stats;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class Season {

    public static final Season
            SEASON_1 = new Season(0L, 1581850800000L, 1),
            SEASON_2 = new Season(1581850800000L, 1589752800000L, 2),
            SEASON_3 = new Season(1589752800000L, 1597528800000L, 3),
            SEASON_4 = new Season(1597528800000L, -1L, 4);

    public static final Season CURRENT = SEASON_3;

    @Getter
    private final long start, end;
    @Getter
    private final int season;

    public boolean wasEndSet() {
        return this.end != -1L;
    }

    public boolean isOver() {
        return System.currentTimeMillis() > this.end;
    }

    public boolean isCurrent() {
        return this.season == Stats.SEASON;
    }

    public boolean hasStarted() {
        return System.currentTimeMillis() >= this.start;
    }

    public boolean isEnding() {
        return wasEndSet() && !isOver() && (this.end - System.currentTimeMillis()) <= TimeUnit.DAYS.toMillis(16);
    }
}
