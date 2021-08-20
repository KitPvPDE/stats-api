package net.kitpvp.stats.redis.value;

import io.lettuce.core.ScoredValue;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class ScoredStringValue extends ScoredValue<String> {

    private final ScoredValue<String> proxy;

    @Override
    public String getValue() {
        return proxy.getValue();
    }

    @Override
    public double getScore() {
        return proxy.getScore();
    }

    @Override
    public String toString() {
        return proxy.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ScoredStringValue that = (ScoredStringValue) o;
        return proxy.equals(that.proxy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), proxy);
    }
}
