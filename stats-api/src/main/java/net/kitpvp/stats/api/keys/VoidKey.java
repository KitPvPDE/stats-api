package net.kitpvp.stats.api.keys;

import net.kitpvp.stats.api.functions.keys.VoidKeyFunction;
import net.kitpvp.stats.builder.keys.VoidKeyBuilder;
import net.kitpvp.stats.reader.Reader;

public interface VoidKey extends Key<Void> {

    static VoidKeyBuilder builder() {
        return new VoidKeyBuilder();
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
