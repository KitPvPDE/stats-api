package net.kitpvp.stats.mongodb.api.async;

import net.kitpvp.mongodbapi.async.Async;

@FunctionalInterface
public interface AsyncRunnable extends Runnable {

    @Override
    void run();

    static AsyncRunnable async(Runnable runnable) {
        return () -> Async.run(runnable);
    }
}
