package me.lindanpeng.qunawan.core.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

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
    public void rightPushAll(String key, Object... objects){
        redisTemplate.opsForList().rightPushAll(key,objects);
    }
    public void rightPush(String key,Object o){
        redisTemplate.opsForList().rightPush(key,o);
    }
    public List<?> range(String key,int start ,int stop){
        return redisTemplate.opsForList().range(key,start,stop);
    }
    public long getSize(String key){
        return redisTemplate.opsForList().size(key);
    }
}
