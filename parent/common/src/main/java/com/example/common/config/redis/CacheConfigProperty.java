package com.example.common.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

@Data
@Primary
@Configuration
@ConfigurationProperties(prefix = "jy.redis")
public class CacheConfigProperty {

  private String serverName = "127.0.0.1";

  private int port = 6379;

  private long timeToLive = 5; // 以分钟为单位

  /**
   *  保存自定义缓存的Ttl
   *  Key: cacheName
   *  value: Ttl
   * */
  private Map<String, Long> cacheExpiration = new HashMap<>();


}
