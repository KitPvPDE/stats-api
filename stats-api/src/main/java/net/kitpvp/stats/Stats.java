package net.kitpvp.stats;

import net.kitpvp.mongodbapi.async.Async;
import net.kitpvp.mongodbapi.async.Sync;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface Stats<Source> {

    boolean MAIN_THREAD_CHECK = System.getProperty("net.kitpvp.stats.checkmain", "true").equals("true");

    UUID getPlayerId();

    StatsReader load(Source source);

    default void load(Source source, Consumer<StatsReader> callback, Executor executor) {
        Async.run(() -> {
            StatsReader statsReader = this.load(source);
            executor.execute(() -> {
                callback.accept(statsReader);
            });
        });
    }

    static void checkForMainThread() {
        if(MAIN_THREAD_CHECK && Sync.isMainThread()){
            throw new IllegalStateException("Synchronous database action via main server thread");
        }
    }
}
