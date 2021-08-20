package net.kitpvp.stats.async;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.Executor;

public interface SyncExecutor extends Executor {

    void executeAsync(@NotNull Runnable command);

    void executeSync(@NotNull Runnable command);

    boolean isMainThread();

    default void execute(@NotNull Runnable command) {
        this.executeAsync(command);
    }

    SyncExecutor DIRECT = new SyncExecutor() {
        @Override
        public void executeAsync(@NotNull Runnable command) {
            this.execute(command);
        }

        @Override
        public void executeSync(@NotNull Runnable command) {
            this.execute(command);
        }

        @Override
        public boolean isMainThread() {
            return false;
        }

        public void execute(@NotNull Runnable command) {
            Objects.requireNonNull(command).run();
        }
    };
}
