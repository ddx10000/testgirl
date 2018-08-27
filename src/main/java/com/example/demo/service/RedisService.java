package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void add(String key, Object object) {
        stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(object));
    }

    public void add(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public <T> void add(String key, List<T> users, Long time) {
        String src = JSON.toJSONString(users);
        stringRedisTemplate.opsForValue().set(key, src, time, TimeUnit.MINUTES);
    }


    public <T> T get(String key,Class<T> tClass) {
        String source = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(source)) {
            return JSON.parseObject(source,tClass);
        }
        return null;
    }

    public <T> List<T> getList(String key,Class<T> tClass) {
        String source = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(source)) {
            return JSON.parseArray(source, tClass);
//            return gson.fromJson(source, new TypeToken<List<User>>() {
//            }.getType());
        }
        return null;
    }

    public void delete(String key) {
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }
}