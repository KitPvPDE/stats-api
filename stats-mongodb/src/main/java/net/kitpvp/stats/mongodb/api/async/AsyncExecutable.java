package net.kitpvp.stats.mongodb.api.async;

import net.kitpvp.mongodbapi.async.Async;
import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.async.Sync;
import net.kitpvp.mongodbapi.log.Log;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.Supplier;

public interface AsyncExecutable extends AsyncTask {

    void execute();

    default void executeAsync() {
        this.executeTaskAsync(this::execute, null, null);
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
