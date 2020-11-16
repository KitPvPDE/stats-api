package net.kitpvp.stats.api.keys;

import net.kitpvp.stats.api.functions.keys.KeyFunctions;
import net.kitpvp.stats.keys.impl.numeric.*;
import net.kitpvp.stats.keys.numeric.*;

import static net.kitpvp.stats.api.functions.keys.KeyFunctions.bind;

public interface Keys {

    static <K> LongSStatsKey bindKey(LongStatsKey<K> statsKey, K key) {
        return new LongVoidStatsKeyImpl(bind(statsKey.keyFunction(), key),
                statsKey.additionLong(), statsKey.inverseLong(), statsKey.neutralLong(),
                statsKey.defLong(), statsKey.offsetLong());
    }

    static <K> LongSSeasonKey bindKey(LongSeasonKey<K> seasonKey, K key) {
        LongSeasonKeyImpl<K> impl = (LongSeasonKeyImpl<K>) seasonKey;
        return new LongVoidSeasonKeyImpl(bind(impl.keyFunction(), key),
                impl.additionLong(), impl.inverseLong(), impl.neutralLong(), impl.defLong(), impl.offsetLong());
    }

    static <K> LongSStageKey bindKey(LongStageKey<K> stageKey, K key) {
        LongStageKeyImpl<K> impl = (LongStageKeyImpl<K>) stageKey;
        return new LongVoidStageKeyImpl(bind(impl.keyFunction(), key),
                impl.additionLong(), impl.inverseLong(), impl.neutralLong(), impl.defLong(), impl.offsetLong());
    }

}
