package com.example.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

  private final RedisTemplate<String, Object> redisTemplate;
  public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * 指定缓存失效时间
   * @param key 键
   * @param time 过期时间， 以秒为单位
   * */
  public Boolean setExpire(String key, long time) {
    try {
      if(time > 0) {
        redisTemplate.expire(key, Duration.ofSeconds( time ));
      }
      return Boolean.TRUE;
    }catch (Exception e) {
      return Boolean.FALSE;
    }
  }

  /**
   * 获取对应键的过期时间
   * @param key 键
   * @return 时间， 以秒为单位
   * */
  public Long getExpire(String key) {
    return redisTemplate.getExpire(key, TimeUnit.SECONDS);
  }


  /**
   * 判断 key 是否存在
   * @param key 键
   * @return Boolean, true 存在 false 不存在
   * */
  public Boolean hasKey(String key) {
    return redisTemplate.hasKey(key);
  }

  /**
   * 添加一个键到缓存中
   * @param key 键名
   * @param value 键值
   * @param time 时间（秒）time == 0 ： 无限期
   * @return void
   * */
  public Boolean put(String key, Object value, long time) {
    try {
      if(time > 0) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
      }else {
        return put(key, value);
      }
      return Boolean.TRUE;
    }catch (Exception e) {
      return Boolean.FALSE;
    }
  }

  /**
   * RedisTemplate 操作函数
   * 1. opsForCluster() 返回集群操作接口
   * 2. opsForHash() Hash值 操作集合
   * 3. opsForList() List值 操作集合
   * 4. opsForSet() Set值 操作集合
   * 5. opsForValue() 对象值 操作集合
   * */
  public Boolean put(String key, Object value) {
    try {
      redisTemplate.opsForValue().set(key, value);
      return true;
    }catch (Exception e) {
      return false;
    }
  }

  /**
   * 获取一个对象
   * @param key 键名
   * */
  public Object get(String key) {
    return key == null ? null : redisTemplate.opsForValue().get(key);
  }
}
