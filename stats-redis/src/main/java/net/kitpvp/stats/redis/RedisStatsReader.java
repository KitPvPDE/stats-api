package net.kitpvp.stats.redis;

import io.lettuce.core.KeyValue;
import io.lettuce.core.ScoredValue;

public class RedisStatsReader {

    private final KeyValue<String, ScoredValue<String>> value;

    public RedisStatsReader(KeyValue<String, ScoredValue<String>> value) {
        this.value = value;
    }

    public String getKey() {
        return value.getKey();
    }

    public String getValue() {
        return value.getValue().getValue();
    }

    public double getScore() {
        return value.getValue().getScore();
    }
}
