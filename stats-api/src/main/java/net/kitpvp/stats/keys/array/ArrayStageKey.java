package net.kitpvp.stats.keys.array;

import net.kitpvp.stats.api.keys.AppendableArrayKey;
import net.kitpvp.stats.keys.StageKey;

import java.util.List;

public interface ArrayStageKey<K, X> extends StageKey<K, List<X>>, AppendableArrayKey<K, X> {
}
