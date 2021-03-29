package net.kitpvp.stats.keys;

import java.util.Set;
import java.util.stream.Stream;

public interface SetVoidSeasonKey<X> extends SetSeasonKey<Void, X>, VoidSeasonKey<Set<X>>, IterableSetVoidKey<X> {

    @Override
    SetVoidStatsKey<X> alltime();

    @Override
    SetVoidStatsKey<X> season();

    @Override
    SetVoidStatsKey<X> season(int season);

    @Override
    default SetVoidSeasonKey<X> bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends SetVoidStatsKey<X>> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
