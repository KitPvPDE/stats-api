package net.kitpvp.stats.mongodb.api.async;

import net.kitpvp.mongodbapi.async.Async;
import net.kitpvp.mongodbapi.async.Sync;

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
        if(Sync.isMainThread()) {
            Async.run(() -> {
                this.execute();
                callback.accept(null);
            });
        } else {
            this.execute();
            callback.accept(null);
        }
    }
}
