package net.kitpvp.stats.api.keys;

import net.kitpvp.stats.keys.numeric.*;

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

}
