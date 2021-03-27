package net.kitpvp.stats.mongodb.api.async;

import net.kitpvp.mongodbapi.async.Executors;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface AsyncExecutable extends AsyncTask {

    void execute();

    default void executeAsync() {
        this.executeTaskAsync(this::execute, (Runnable) null, null);
    }

    default void executeAsync(Consumer<Void> callback) {
        this.executeTaskAsync(this::execute, callback, Executors.DIRECT);
    }

    default void executeAsync(Consumer<Void> callback, Executor executor) {
        this.executeTaskAsync(this::execute, callback, executor);
    }

    default void executeAsync(Runnable callback) {
        this.executeAsync(callback, Executors.DIRECT);
    }

    default void executeAsync(Runnable callback, Executor executor) {
        this.executeTaskAsync(this::execute, (x) -> callback.run(), executor);
    }
}
