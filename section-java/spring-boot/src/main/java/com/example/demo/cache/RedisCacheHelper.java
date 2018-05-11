package com.example.demo.cache;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheHelper {

    private RedisTemplate redisTemplate;

    /**
     * 将redis 中的key 和 value序列化
     * @param redisTemplate
     */
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new StringRedisSerializer());
    }

    /**
     * 从redis 中获得数据
     * @param key
     * @param clazz
     * @return
     */
    public Object get(String key,Class clazz){
        if (redisTemplate.hasKey(key)){
            String json = (String) redisTemplate.opsForValue().get(key);
            return new Gson().fromJson(json,clazz);
        }
        return null;
    }

    /**
     * 向redis 中存值
     * @param kev
     * @param o
     */
    public void set(String kev,Object o){
        String json = new Gson().toJson(o);
        redisTemplate.opsForValue().set(kev,json);
    }

    /**
     * 向redis 中存值并设置过期时间
     * @param key
     * @param o
     * @param time
     */
    public void set(String key,Object o,Long time){
        redisTemplate.opsForValue().set(key,new Gson().toJson(o),time,TimeUnit.SECONDS);
    }
}
