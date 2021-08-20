package net.kitpvp.stats.async;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

import static net.kitpvp.stats.async.SyncExecutor.DIRECT;

public interface AsyncExecutable extends AsyncTask {

    void execute();

    default void executeAsync() {
        this.executeTaskAsync(this::execute, (Runnable) null, null);
    }

    default void executeAsync(Consumer<Void> callback) {
        this.executeTaskAsync(this::execute, callback, DIRECT);
    }

    default void executeAsync(Consumer<Void> callback, Executor executor) {
        this.executeTaskAsync(this::execute, callback, executor);
    }

    default void executeAsync(Runnable callback) {
        this.executeAsync(callback, DIRECT);
    }

    default void executeAsync(Runnable callback, Executor executor) {
        this.executeTaskAsync(this::execute, (x) -> callback.run(), executor);
    }
}
