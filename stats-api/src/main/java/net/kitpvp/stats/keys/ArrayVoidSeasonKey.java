package net.kitpvp.stats.keys;

import java.util.List;
import java.util.stream.Stream;

public interface ArrayVoidSeasonKey<X> extends ArraySeasonKey<Void, X>, VoidSeasonKey<List<X>>, IterableArrayVoidKey<X> {

    @Override
    ArrayVoidStatsKey<X> season();

    @Override
    ArrayVoidStatsKey<X> alltime();

    @Override
    ArrayVoidStatsKey<X> season(int season);

    @Override
    default ArrayVoidSeasonKey<X> bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends ArrayVoidStatsKey<X>> stream() {
        return Stream.of(this.alltime(), this.season());
    }
}
