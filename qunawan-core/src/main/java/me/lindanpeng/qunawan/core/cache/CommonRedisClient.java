package me.lindanpeng.qunawan.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommonRedisClient {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    public void set(String key,Object o){
        redisTemplate.opsForValue().set(key,o);
    }
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public void del(String key){ redisTemplate.delete(key);}
}
