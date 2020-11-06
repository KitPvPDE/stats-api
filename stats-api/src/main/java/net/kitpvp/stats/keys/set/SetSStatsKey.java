package net.kitpvp.stats.keys.set;

import net.kitpvp.stats.keys.SStatsKey;
import org.bson.Document;

import java.util.List;
import java.util.Set;

public interface SetSStatsKey<X> extends SetStatsKey<Void, X>, SStatsKey<Set<X>> {

    @Override
    Set<X> def();

    @Override
    String key(Void unused);

    @Override
    Set<X> apply(Set<X> vs);
}
