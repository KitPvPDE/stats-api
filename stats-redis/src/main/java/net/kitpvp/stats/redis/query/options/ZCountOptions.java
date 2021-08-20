package net.kitpvp.stats.redis.query.options;

import io.lettuce.core.Range;
import net.kitpvp.stats.redis.query.RedisCommandOptions;

public class ZCountOptions implements RedisCommandOptions {

    private final Range<Long> range = Range.unbounded();

    public ZCountOptions gte(long lower) {
        this.range.gte(lower);
        return this;
    }

    public ZCountOptions gt(long lower) {
        this.range.gt(lower);
        return this;
    }

    public ZCountOptions lte(long upper) {
        this.range.lte(upper);
        return this;
    }

    public ZCountOptions lt(long upper) {
        this.range.lt(upper);
        return this;
    }

    public Range<Long> range() {
        return this.range;
    }
}
