package net.kitpvp.stats.keys.inc;

import net.kitpvp.stats.api.keys.AppendableIncKey;
import net.kitpvp.stats.keys.StageKey;

public interface IncStageKey<K, V> extends StageKey<K, V>, AppendableIncKey<K, V> { }
