package net.kitpvp.stats.mongodb.api.async;

import net.kitpvp.mongodbapi.async.Async;
import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.async.Sync;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface AsyncExecutable {

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

    default void executeTaskAsync(Runnable executable) {
        if(Sync.isMainThread()) {
            Async.run(executable);
        } else {
            executable.run();
        }
    }

    @Contract(value = "_, !null, null -> fail")
    default void executeTaskAsync(Runnable task, @Nullable Consumer<Void> callback, Executor executor) {
        this.executeTaskAsync(() -> {
            task.run();

            if(callback != null) {
                executor.execute(() -> callback.accept(null));
            }
        });
    }

    @Contract(value = "_, !null, null -> fail")
    default <T> void executeTaskAsync(Supplier<T> task, @Nullable Consumer<T> callback, Executor executor) {
        Runnable executable = () -> {
            T t = task.get();

            if(callback != null) {
                executor.execute(() -> callback.accept(t));
            }
        };
        this.executeTaskAsync(executable);
    }
}
