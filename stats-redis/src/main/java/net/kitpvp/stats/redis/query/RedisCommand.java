package net.kitpvp.stats.redis.query;

import net.kitpvp.stats.redis.query.options.ZAddOptions;
import net.kitpvp.stats.redis.query.options.ZCountOptions;

public enum RedisCommand {

    BZPOPMIN,
    BZPOPMAX,
    ZADD(ZAddOptions.class),
    ZCARD,
    ZCOUNT(ZCountOptions.class),
    ZDIFF,
    ZRANGE,
    ZRANK,
    ZREM,
    ZDIFFSTORE,
    ZINCRBY,
    ZINTER,
    ZINTERCARD,
    ZINTERSTORE,
    ZLEXCOUNT,
    ZPOPMAX,
    ZPOPMIN,
    ZRANDMEMBER,
    ZRANGESTORE,
    ZRANGEBYLEX,
    ZREVRANGEBYLEX,
    ZRANGEBYSCORE,
    ZREMRANGEBYLEX,
    ZREMRANGEBYRANK,
    ZREVRANK,
    ZUNIONSTORE,
    ZSCAN,
    ;

    private final Class<? extends RedisCommandOptions> optionsClass;

    RedisCommand() {
        this.optionsClass = null;
    }

    RedisCommand(Class<? extends RedisCommandOptions> optionsClass) {
        this.optionsClass = optionsClass;
    }

    public Class<? extends RedisCommandOptions> getOptionsClass() {
        return optionsClass;
    }
}
