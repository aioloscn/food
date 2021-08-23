local key = KEYS[1];
local threadId = ARGV[1];

if (redis.call('hexists', key, threadId) == 0) then -- 判断当前锁是否还是被自己持有，如果不是自己，则直接返回
    return nil;
end;
local count = redis.call('hincrby', key, threadId, -1); -- 是自己的锁，则重入次数-1

if (count == 0) then
    redis.call('del', key);
    return nil;
end;