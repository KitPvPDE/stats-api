package net.kitpvp.api.stats.keys.array;

import net.kitpvp.api.stats.keys.SStatsKey;
import org.bson.Document;

import java.util.List;

public interface ArraySStatsKey<X, V extends List<X>> extends ArrayStatsKey<Void, X, V>, SStatsKey<V> {

    @Override
    V getDefault();

    @Override
    Document append(Document to, Void unused, V vs);

    @Override
    String getKey(Void unused);

    @Override
    V apply(V vs);
}
