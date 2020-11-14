package net.kitpvp.stats.keys.array;

import net.kitpvp.stats.builder.array.ArrayVoidKeyBuilder;
import net.kitpvp.stats.keys.SStatsKey;

import java.util.List;

public interface ArraySStatsKey<X> extends ArrayStatsKey<Void, X>, SStatsKey<List<X>> {

    static <X> ArrayVoidKeyBuilder<X> builder() {
        return new ArrayVoidKeyBuilder<>();
    }

    @Override
    List<X> def();

    @Override
    String key(Void unused);

    @Override
    List<X> apply(List<X> vs);
}
