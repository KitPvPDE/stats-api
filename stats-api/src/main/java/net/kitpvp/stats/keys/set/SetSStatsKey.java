package net.kitpvp.stats.keys.set;

import net.kitpvp.stats.builder.set.SetVoidKeyBuilder;
import net.kitpvp.stats.keys.SStatsKey;

import java.util.Set;

public interface SetSStatsKey<X> extends SetStatsKey<Void, X>, SStatsKey<Set<X>> {

    static <X> SetVoidKeyBuilder<X> builder() {
        return new SetVoidKeyBuilder<>();
    }

    @Override
    Set<X> def();

    @Override
    String key(Void unused);

    @Override
    Set<X> apply(Set<X> vs);
}
