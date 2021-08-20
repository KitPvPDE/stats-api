package net.kitpvp.stats.redis.query.options;

import io.lettuce.core.ZAddArgs;
import net.kitpvp.stats.redis.query.RedisCommandOptions;

public class ZAddOptions implements RedisCommandOptions {

    private final ZAddArgs args = new ZAddArgs();

    public ZAddOptions nx() {
        this.args.nx();
        return this;
    }

    public ZAddOptions xx() {
        this.args.xx();
        return this;
    }

    public ZAddOptions ch() {
        this.args.ch();
        return this;
    }

    public ZAddOptions gt() {
        this.args.gt();
        return this;
    }

    public ZAddOptions lt() {
        this.args.lt();
        return this;
    }

    public ZAddArgs args() {
        return this.args;
    }
}
