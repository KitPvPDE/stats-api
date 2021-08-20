package net.kitpvp.stats.redis.connection;

import io.lettuce.core.api.StatefulRedisConnection;
import net.kitpvp.stats.async.SyncExecutor;

public interface RedisConnection {

    StatefulRedisConnection<String, String> connection();

    SyncExecutor syncExecutor();
}
