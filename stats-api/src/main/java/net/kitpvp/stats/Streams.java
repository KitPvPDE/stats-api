package net.kitpvp.stats;

import net.kitpvp.stats.keys.*;
import net.kitpvp.stats.season.Season;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public interface Streams {

    static <K> Stream<IntStatsKey<K>> stream(IntSeasonKey<K> seasonKey) {
        return Stream.of(seasonKey.alltime(), seasonKey.season());
    }

    static <K> Stream<IntStatsKey<K>> stream(IntStageKey<K> stageKey) {
        return Stream.of(stageKey.alltime(), stageKey.season(), stageKey.stage());
    }

    static <K> Stream<LongStatsKey<K>> stream(LongSeasonKey<K> seasonKey) {
        return Stream.of(seasonKey.alltime(), seasonKey.season());
    }

    static <K> Stream<LongStatsKey<K>> stream(LongStageKey<K> stageKey) {
        return Stream.of(stageKey.alltime(), stageKey.season(), stageKey.stage());
    }

    static Stream<LongVoidStatsKey> stream(LongVoidSeasonKey seasonKey) {
        return Stream.of(seasonKey.alltime(), seasonKey.season());
    }

    static Stream<LongVoidStatsKey> stream(LongVoidStageKey stageKey) {
        return Stream.of(stageKey.alltime(), stageKey.season(), stageKey.stage());
    }

    static Stream<LongVoidStatsKey> seasons(LongVoidSeasonKey seasonKey) {
        List<LongVoidStatsKey> keys = new LinkedList<>();
        for(int season = 1; season <= Season.getSeason(); season++) {
            keys.add(seasonKey.season(season));
        }
        return keys.stream();
    }

    static <K> Stream<LongStatsKey<K>> seasons(LongSeasonKey<K> seasonKey) {
        List<LongStatsKey<K>> keys = new LinkedList<>();
        for(int season = 1; season <= Season.getSeason(); season++) {
            keys.add(seasonKey.season(season));
        }
        return keys.stream();
    }

    static Stream<IntVoidStatsKey> seasons(IntVoidSeasonKey seasonKey) {
        List<IntVoidStatsKey> keys = new LinkedList<>();
        for(int season = 1; season <= Season.getSeason(); season++) {
            keys.add(seasonKey.season(season));
        }
        return keys.stream();
    }

    static <K> Stream<IntStatsKey<K>> seasons(IntSeasonKey<K> seasonKey) {
        List<IntStatsKey<K>> keys = new LinkedList<>();
        for(int season = 1; season <= Season.getSeason(); season++) {
            keys.add(seasonKey.season(season));
        }
        return keys.stream();
    }
}
