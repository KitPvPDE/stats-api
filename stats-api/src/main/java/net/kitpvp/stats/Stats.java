package net.kitpvp.stats;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface Stats {

    UUID getPlayerId();

    StatsReader load();

    @Deprecated
    default StatsReader load(Object source) {
        return load();
    }

    default void loadAsync(Consumer<StatsReader> callback, Executor executor) {
        executor.execute(() -> callback.accept(this.load()));
    }
}
