package net.kitpvp.stats.keys;

import java.util.List;
import java.util.stream.Stream;

public interface IterableArrayVoidKey<X> extends IterableArrayKey<Void, X>, IterableVoidStatsKey<List<X>> {

    @Override
    Stream<? extends ArrayVoidStatsKey<X>> stream();
}
