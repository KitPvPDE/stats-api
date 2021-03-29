package net.kitpvp.stats.keys;

import java.util.List;
import java.util.stream.Stream;

public interface ArrayVoidStageKey<X> extends ArrayStageKey<Void, X>, VoidStageKey<List<X>>, IterableArrayVoidKey<X> {

    @Override
    ArrayVoidStatsKey<X> alltime();

    @Override
    ArrayVoidStatsKey<X> season();

    @Override
    ArrayVoidStatsKey<X> stage();

    @Override
    ArrayVoidStatsKey<X> season(int season);

    @Override
    default Stream<? extends ArrayVoidStatsKey<X>> stream() {
        return Stream.of(this.alltime(), this.season(), this.stage());
    }

    @Override
    default ArrayVoidStageKey<X> bind(Void unused) {
        return this;
    }
}
