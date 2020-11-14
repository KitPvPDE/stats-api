package net.kitpvp.stats.mongodb.api.async;

import net.kitpvp.mongodbapi.async.Async;
import net.kitpvp.mongodbapi.async.Executors;
import net.kitpvp.mongodbapi.async.Sync;

import java.util.concurrent.Executor;
import java.util.function.Consumer;

public interface AsyncExecutable {

    void execute();

    default void executeAsync() {
        if(Sync.isMainThread()){
            Async.run(this::execute);
        }else{
            this.execute();
        }
    }

    default void executeAsync(Consumer<Void> callback) {
        this.executeAsync(callback, Executors.DIRECT);
    }

    default void executeAsync(Consumer<Void> callback, Executor executor) {
        if(Sync.isMainThread()) {
            Async.run(() -> {
                this.execute();

                executor.execute(() -> {
                    callback.accept(null);
                });
            });
        } else {
            this.execute();

            executor.execute(() -> {
                callback.accept(null);
            });
        }
    }

    default void executeAsync(Runnable callback) {
        this.executeAsync(callback, Executors.DIRECT);
    }

    default void executeAsync(Runnable callback, Executor executor) {
        if(Sync.isMainThread()) {
            Async.run(() -> {
                this.execute();

                executor.execute(callback);
            });
        } else {
            this.execute();

            executor.execute(callback);
        }
    }
}
