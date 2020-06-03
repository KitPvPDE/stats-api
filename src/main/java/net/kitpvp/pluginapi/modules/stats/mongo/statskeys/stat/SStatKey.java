package net.kitpvp.pluginapi.modules.stats.mongo.statskeys.stat;

import net.kitpvp.pluginapi.modules.stats.mongo.statskeys.SStatsKey;

import java.util.function.Function;
import java.util.function.Supplier;

class SStatKey<V> extends StatKey<Void, V> implements SStatsKey<V> {

    private final String key;

    public SStatKey(V def, Function<Void, String> toKey, Function<V, V> toValue) {
        super(def, toKey, toValue);

        this.key = null;
    }

    public SStatKey(Supplier<V> toDefault, Function<Void, String> toKey, Function<V, V> toValue) {
        super(toDefault, toKey, toValue);

        this.key = null;
    }

    public SStatKey(V def, String key, Function<V, V> toValue) {
        super(def, null, toValue);

        this.key = key;
    }

    public SStatKey(Supplier<V> toDefault, String key, Function<V, V> toValue) {
        super(toDefault, null, toValue);

        this.key = key;
    }

    @Override
    public String getKey(Void aVoid) {
        if(this.key != null)
            return this.key;

        return super.getKey(aVoid);
    }
}
