package net.kitpvp.stats;

import net.kitpvp.mongodbapi.async.Async;
import net.kitpvp.mongodbapi.async.Sync;
import net.kitpvp.stats.api.Countable;
import net.kitpvp.stats.api.Findable;
import net.kitpvp.stats.api.Writable;

import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface Stats<Source, T> extends Findable<Source, T>, Countable<Source, T>, Writable<Source, T> {

    UUID getPlayerId();

    default T load(Source source) {
        return this.find(source).first();
    }

    default void load(Source source, Consumer<T> callback, Executor executor) {
        Async.run(() -> {
            T statsReader = this.load(source);
            executor.execute(() -> {
                callback.accept(statsReader);
            });
        });
    }

    static void checkForMainThread() {
        if(Sync.isMainThread()){
            throw new IllegalStateException("Synchronous database action via main server thread");
        }
    }
}
