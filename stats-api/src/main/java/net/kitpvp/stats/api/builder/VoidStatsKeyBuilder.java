package net.kitpvp.stats.api.builder;

import net.kitpvp.stats.keys.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

public interface VoidStatsKeyBuilder<V> extends ComponentBuilder<VoidStatsKey<V>> {

    @NotNull VoidStatsKey<V> build();

    @NotNull VoidSeasonKey<V> season();

    @NotNull VoidStageKey<V> stage(UnaryOperator<VoidKeyFunction> remapFunction);

}
