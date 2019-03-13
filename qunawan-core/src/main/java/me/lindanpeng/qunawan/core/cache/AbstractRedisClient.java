package me.lindanpeng.qunawan.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class AbstractRedisClient<T> {
    private String keyPrefix;
    @Autowired
    protected RedisTemplate<String,Object> redisTemplate;

    AbstractRedisClient(String keyPrefix) {
        this.keyPrefix = keyPrefix;

    }

    public void set(String key, T value,long expireIn) {
        String realKey=getRealKey(key);
        redisTemplate.opsForValue().set(realKey,value);
    }

    public T get(String key) {
        String realKey=getRealKey(key);
        return (T) redisTemplate.opsForValue().get(realKey);
    }
    private String getRealKey(String key){
        String realKey=this.keyPrefix+":"+key;
        return realKey;
    }
}
