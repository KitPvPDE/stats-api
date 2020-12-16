package net.kitpvp.stats.api.keys;

import net.kitpvp.stats.keys.numeric.*;
import net.kitpvp.stats.season.Season;

import java.util.Collections;
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

    static Stream<LongSStatsKey> stream(LongSSeasonKey seasonKey) {
        return Stream.of(seasonKey.alltime(), seasonKey.season());
    }

    static Stream<LongSStatsKey> stream(LongSStageKey stageKey) {
        return Stream.of(stageKey.alltime(), stageKey.season(), stageKey.stage());
    }

    static Stream<LongSStatsKey> seasons(LongSSeasonKey seasonKey) {
        List<LongSStatsKey> keys = new LinkedList<>();
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

    static Stream<IntSStatsKey> seasons(IntSSeasonKey seasonKey) {
        List<IntSStatsKey> keys = new LinkedList<>();
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
