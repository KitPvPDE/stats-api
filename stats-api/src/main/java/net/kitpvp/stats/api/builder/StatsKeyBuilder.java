package net.kitpvp.stats.api.builder;

import net.kitpvp.stats.keys.SeasonKey;
import net.kitpvp.stats.keys.StageKey;
import net.kitpvp.stats.keys.StatsKey;
import org.jetbrains.annotations.NotNull;

public interface StatsKeyBuilder<K, V> extends ComponentBuilder<StatsKey<K, V>> {

    @NotNull StatsKey<K, V> build();

    @NotNull SeasonKey<K, V> season();

    @NotNull StageKey<K, V> stage();
}
