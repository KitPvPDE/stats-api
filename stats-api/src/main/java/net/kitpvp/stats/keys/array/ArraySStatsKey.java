package net.kitpvp.stats.keys.array;

import net.kitpvp.stats.keys.SStatsKey;

import java.util.Collection;
import java.util.List;

public interface ArraySStatsKey<X> extends ArrayStatsKey<Void, X>, SStatsKey<List<X>> {

    @Override
    List<X> def();

    @Override
    String key(Void unused);

    @Override
    List<X> apply(List<X> vs);
}
