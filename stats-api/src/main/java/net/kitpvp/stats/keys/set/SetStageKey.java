package net.kitpvp.stats.keys.set;

import net.kitpvp.stats.api.keys.AppendableSetKey;
import net.kitpvp.stats.keys.StageKey;

import java.util.Set;

public interface SetStageKey<K, X> extends StageKey<K, Set<X>>, AppendableSetKey<K, X> { }
