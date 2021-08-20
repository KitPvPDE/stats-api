package net.kitpvp.stats.async;

import net.kitpvp.stats.async.SyncExecutor;
import net.kitpvp.stats.function.BooleanBiConsumer;
import net.kitpvp.stats.function.BooleanConsumer;
import net.kitpvp.stats.function.BooleanTriConsumer;
import net.kitpvp.stats.function.BooleanUnaryOperator;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.Executor;
import java.util.function.*;

public interface AsyncTask {

    SyncExecutor syncExecutor();

    default void executeTaskAsync(Runnable executable) {
        if (syncExecutor().isMainThread()) {
            syncExecutor().executeAsync(executable);
        } else {
            executable.run();
        }
    }

    @Contract(value = "_, !null, null -> fail")
    default void executeTaskAsync(Runnable task, @Nullable Consumer<Void> callback, Executor executor) {
        this.executeTaskAsync(() -> {
            task.run();

            if (callback != null) {
                executor.execute(() -> callback.accept(null));
            }
        });
    }

    @Contract(value = "_, !null, null -> fail")
    default void executeTaskAsync(Runnable task, @Nullable Runnable callback, Executor executor) {
        this.executeTaskAsync(() -> {
            task.run();

            if (callback != null) {
                executor.execute(callback);
            }
        });
    }

    @Contract(value = "_, !null, null -> fail")
    default <T> void executeTaskAsync(Supplier<T> task, @Nullable Consumer<T> callback, Executor executor) {
        Runnable executable = () -> {
            T t = task.get();

            if (callback != null) {
                executor.execute(() -> callback.accept(t));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, _, !null, null -> fail")
    default <T> void executeTaskAsync(Consumer<T> task, T t, @Nullable Consumer<Void> callback, Executor executor) {
        Runnable executable = () -> {
            task.accept(t);

            if (callback != null) {
                executor.execute(() -> callback.accept(null));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, _, !null, null -> fail")
    default <T, R> void executeTaskAsync(Function<T, R> task, T t, @Nullable Consumer<R> callback, Executor executor) {
        Runnable executable = () -> {
            R r = task.apply(t);

            if (callback != null) {
                executor.execute(() -> callback.accept(r));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, !null, null -> fail")
    default void executeTaskAsync(LongSupplier task, @Nullable LongConsumer callback, Executor executor) {
        Runnable executable = () -> {
            long l = task.getAsLong();

            if (callback != null) {
                executor.execute(() -> callback.accept(l));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, _, !null, null -> fail")
    default <T> void executeTaskAsync(LongConsumer task, long l, @Nullable Consumer<Void> callback, Executor executor) {
        Runnable executable = () -> {
            task.accept(l);

            if (callback != null) {
                executor.execute(() -> callback.accept(null));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, _, !null, null -> fail")
    default void executeTaskAsync(LongUnaryOperator task, long l, @Nullable LongConsumer callback, Executor executor) {
        Runnable executable = () -> {
            long r = task.applyAsLong(l);

            if (callback != null) {
                executor.execute(() -> callback.accept(r));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, !null, null -> fail")
    default void executeTaskAsync(BooleanSupplier task, @Nullable BooleanConsumer callback, Executor executor) {
        Runnable executable = () -> {
            boolean b = task.getAsBoolean();

            if (callback != null) {
                executor.execute(() -> callback.accept(b));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, _, !null, null -> fail")
    default <T> void executeTaskAsync(BooleanConsumer task, boolean b, @Nullable Consumer<Void> callback, Executor executor) {
        Runnable executable = () -> {
            task.accept(b);

            if (callback != null) {
                executor.execute(() -> callback.accept(null));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, _, _, !null, null -> fail")
    default <T> void executeTaskAsync(BooleanBiConsumer task, boolean left, boolean right, @Nullable Consumer<Void> callback, Executor executor) {
        Runnable executable = () -> {
            task.accept(left, right);

            if (callback != null) {
                executor.execute(() -> callback.accept(null));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, _, _, _, !null, null -> fail")
    default <T> void executeTaskAsync(BooleanTriConsumer task, boolean left, boolean center, boolean right, @Nullable Consumer<Void> callback, Executor executor) {
        Runnable executable = () -> {
            task.accept(left, center, right);

            if (callback != null) {
                executor.execute(() -> callback.accept(null));
            }
        };
        this.executeTaskAsync(executable);
    }

    @Contract(value = "_, _, !null, null -> fail")
    default void executeTaskAsync(BooleanUnaryOperator task, boolean b, @Nullable BooleanConsumer callback, Executor executor) {
        Runnable executable = () -> {
            boolean r = task.applyAsBoolean(b);

            if (callback != null) {
                executor.execute(() -> callback.accept(r));
            }
        };
        this.executeTaskAsync(executable);
    }
}
