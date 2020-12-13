package net.kitpvp.stats.keys.bool;

import net.kitpvp.stats.builder.bool.BooleanVoidKeyBuilder;
import net.kitpvp.stats.builder.numeric.IntVoidKeyBuilder;
import net.kitpvp.stats.keys.SStatsKey;

public interface BooleanSStatsKey extends BooleanStatsKey<Void>, SStatsKey<Boolean> {

    static BooleanVoidKeyBuilder builder() {
        return new BooleanVoidKeyBuilder();
    }

    @Override
    Boolean def();

    @Override
    boolean defBoolean();

    @Override
    Boolean apply(Boolean integer);

    @Override
    boolean applyBoolean(boolean b);

    @Override
    String key(Void unused);
}
