package net.kitpvp.stats.redis.query;

import net.kitpvp.stats.async.AsyncTask;
import net.kitpvp.stats.async.SyncExecutor;
import net.kitpvp.stats.redis.RedisStatsReader;
import net.kitpvp.stats.redis.connection.RedisConnection;
import net.kitpvp.stats.redis.query.options.ZAddOptions;
import net.kitpvp.stats.redis.query.options.ZCountOptions;
import net.kitpvp.stats.redis.value.ScoredStringValue;
import net.kitpvp.stats.reference.ReferenceCounted;

import java.util.List;

public final class RedisQuery extends ReferenceCounted implements AsyncTask {

    private final RedisConnection connection;
    private RedisCommand command;
    private RedisCommandOptions options;
    private String[] keys;
    private ScoredStringValue[] values;
    private int timeout;

    public RedisQuery(RedisConnection connection) {
        this.connection = connection;
    }

    public RedisQuery value(ScoredStringValue value) {
        this.values = new ScoredStringValue[] {value};
        return this;
    }

    public RedisQuery values(ScoredStringValue... values) {
        this.values = values;
        return this;
    }

    public RedisQuery command(RedisCommand command) {
        this.command = command;
        return this;
    }

    public RedisQuery command(RedisCommand command, RedisCommandOptions options) {
        this.command = command;
        this.options = options;
        return this;
    }

    public RedisQuery key(String key) {
        this.keys = new String[]{key};
        return this;
    }

    public RedisQuery keys(String... keys) {
        this.keys = keys;
        return this;
    }

    public RedisQuery timeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public RedisQuery keys(List<String> keys) {
        return this.keys(keys.toArray(new String[0]));
    }

    @Override
    public SyncExecutor syncExecutor() {
        return connection.syncExecutor();
    }

    public RedisStatsReader get() {
        return this.get0(RedisStatsReader.class);
    }

    public long getAsLong() {
        return this.get0(Long.class);
    }

    public <T> T getAs(Class<T> returnType) {
        return this.get0(returnType);
    }

    private <T> T get0(Class<T> type) {
        if(this.command.getOptionsClass() != null && this.options == null) {
            throw new NullPointerException("" + this.command + " requires options of type " + this.command.getOptionsClass());
        } else if(this.command.getOptionsClass() != null && !this.command.getOptionsClass().equals(this.options.getClass())) {
            throw new IllegalArgumentException("options of " + this.command + " dont match required type " + this.command.getOptionsClass() + " ( " + this.options.getClass() + ")");
        }

        switch (this.command) {
            case BZPOPMIN:
            case BZPOPMAX:
                if(keys == null)
                    throw new IllegalArgumentException("keys cannot be null in " + this.command);

                if(timeout < 0)
                    throw new IllegalArgumentException("timeout cannot be <0 in " + this.command);
            case ZADD:
                if(keys == null)
                    throw new IllegalArgumentException("keys cannot be null in " + this.command);

                if(keys.length != 1)
                    throw new IllegalArgumentException("only one key allowed in " + this.command);
                break;
            case ZCARD:
                if(keys.length != 1)
                    throw new IllegalArgumentException("only one key allowed in " + this.command);
                break;
        }

        try (RedisQuery ignored = this) {
            switch (this.command) {
                case BZPOPMIN:
                    return type.cast(new RedisStatsReader(this.connection.connection().sync().bzpopmin(this.timeout, keys)));
                case BZPOPMAX:
                    return type.cast(new RedisStatsReader(this.connection.connection().sync().bzpopmax(this.timeout, keys)));
                case ZADD: {
                    ZAddOptions options = (ZAddOptions) this.options;
                    return type.cast(this.connection.connection().sync().zadd(keys[0], options.args(), values));
                }
                case ZCARD:
                    return type.cast(this.connection.connection().sync().zcard(keys[0]));
                case ZCOUNT: {
                    ZCountOptions options = (ZCountOptions) this.options;
                    return type.cast(this.connection.connection().sync().zcount(keys[0], options.range()));
                }
                case ZDIFF: {
                    return type.cast(this.connection.connection().sync().zdiff());
                }
                default:
                    throw new UnsupportedOperationException("" + this.command);
            }
        }
    }
}
