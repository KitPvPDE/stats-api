package net.kitpvp.stats.keys;

import net.kitpvp.stats.builder.VoidBuilder;

public interface SStatsKey<V> extends StatsKey<Void, V> {

    static <V> VoidBuilder<V> builder() {
        return new VoidBuilder<>();
    }
}
