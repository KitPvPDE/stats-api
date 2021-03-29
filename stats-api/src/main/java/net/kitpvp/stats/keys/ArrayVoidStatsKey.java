package net.kitpvp.stats.keys;

import java.util.List;
import java.util.stream.Stream;

public interface ArrayVoidStatsKey<X> extends ArrayStatsKey<Void, X>, VoidStatsKey<List<X>>, IterableArrayVoidKey<X> {

    static <X> ArrayVoidKeyBuilder<X> builder() {
        return new ArrayVoidKeyBuilder<>();
    }

    @Override
    List<X> def();

    @Override
    String key(Void unused);

    @Override
    List<X> apply(List<X> vs);

    @Override
    VoidKeyFunction keyFunction();

    @Override
    default ArrayVoidStatsKey<X> bind(Void unused) {
        return this;
    }

    @Override
    default Stream<? extends ArrayVoidStatsKey<X>> stream() {
        return Stream.of(this);
    }
}
