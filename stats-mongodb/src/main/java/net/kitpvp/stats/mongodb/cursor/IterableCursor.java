package net.kitpvp.stats.mongodb.cursor;

import com.mongodb.Function;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.lang.Nullable;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

@RequiredArgsConstructor
public class IterableCursor<TResult> implements MongoIterable<TResult> {

    private final MongoIterable<TResult> iterable;

    @Override
    public MongoCursor<TResult> iterator() {
        return iterable.iterator();
    }

    @Override
    public @NotNull MongoCursor<TResult> cursor() {
        return iterable.cursor();
    }

    @Override
    @Nullable
    public TResult first() {
        return iterable.first();
    }

    @Override
    public <U> @NotNull IterableCursor<U> map(@NotNull Function<TResult, U> function) {
        return new IterableCursor<>(iterable.map(function));
    }

    @Override
    public <A extends Collection<? super TResult>> @NotNull A into(@NotNull A objects) {
        return iterable.into(objects);
    }

    @Override
    public @NotNull MongoIterable<TResult> batchSize(int i) {
        return iterable.batchSize(i);
    }
}
