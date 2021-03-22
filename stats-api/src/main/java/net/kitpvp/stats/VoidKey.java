package net.kitpvp.stats;

import net.kitpvp.stats.keys.IterableVoidKey;
import net.kitpvp.stats.keys.VoidKeyFunction;
import net.kitpvp.stats.keys.VoidKeyBuilder;
import net.kitpvp.stats.reader.Reader;

public interface VoidKey extends Key<Void>, IterableVoidKey {

    static VoidKeyBuilder builder() {
        return new VoidKeyBuilder();
    }

    static VoidKey key(String path) {
        return builder().path(path).buildKey();
    }

    @Override
    VoidKeyFunction keyFunction();

    @Override
    default String key(Void key) {
        return this.keyFunction().key();
    }

    default String key() {
        return this.keyFunction().key();
    }

    default boolean contains(Reader reader) {
        return this.contains(reader, null);
    }
}
