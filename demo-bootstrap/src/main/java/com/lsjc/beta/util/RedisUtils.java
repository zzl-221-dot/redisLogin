package com.lsjc.beta.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    public <E> void set(String key, E value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public <E> E getSingle(String key, Class<E> cls) {
        Object res = redisTemplate.opsForValue().get(key);
        return cast(res, cls);
    }

    public <E> List<E> getList(String key, Class<E> cls) {
        Object res = redisTemplate.opsForValue().get(key);
        return castList(res, cls);
    }

    public Object get(String key) {
        Object res = redisTemplate.opsForValue().get(key);
        return res;
    }

    public String getString(String key) {
        Object resObject = redisTemplate.opsForValue().get(key);
//        if(ObjectUtil.)
        String res = String.valueOf(redisTemplate.opsForValue().get(key));
        return res;
    }

    public Boolean checkIsRedis() {
        try {
            this.set("test", "test");
            if (StringUtil.isNotEmpty(this.getString("test"))) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    @SuppressWarnings("unchecked")
    private static <T> T cast(Object obj, Class<T> cls) {
        return (T) obj;
    }


    @SuppressWarnings("unchecked")
    private static <T> List<T> castList(Object obj, Class<T> cls) {
        return (List<T>) obj;
    }
}