package com.example.common.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


/**
 * 创建 redis 相关配置
 * @author zzh
 * @since 2020.11.05
 * */
@Configuration
@EnableConfigurationProperties(CacheConfigProperty.class)
public class RedisCacheConfig {

  private final String serverName;
  private final int port;

  public RedisCacheConfig(CacheConfigProperty property) {
    this.serverName = property.getServerName();
    this.port = property.getPort();
  }


  /** 可能会有问题， Duration 可能与上层JDK不兼容 */
  private RedisCacheConfiguration createCacheConfiguration(long timeToLiveSecond) {
    return RedisCacheConfiguration
        .defaultCacheConfig()
        .entryTtl(Duration.ofSeconds(timeToLiveSecond));
  }

  /**
   * 1. Multiple LettuceConnections share a single thread-safe native connection by default.
   * 2. LettuceConnectionFactory should be configured using an environmental configuration
   *    and the client configuration. Lettuce supports the following environmental
   *    configurations:
   *
   *    * RedisStandaloneConfiguration
   *    * RedisStaticMasterReplicaConfiguration
   *    * RedisSocketConfiguration
   *    * RedisSentinelConfiguration
   *    * RedisClusterConfiguration
   *
   * @return LettuceConnectionFactory
   * */
  @Bean
  public LettuceConnectionFactory lettuceConnectionFactory() {
    return new LettuceConnectionFactory(new RedisStandaloneConfiguration(serverName, port));
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    RedisSerializer<String> keySerializer = new StringRedisSerializer(); // key serializer
    Jackson2JsonRedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer<>(Object.class); // value serializer

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

    template.setKeySerializer(keySerializer);
    template.setValueSerializer(valueSerializer);

    template.setHashKeySerializer(keySerializer);
    template.setValueSerializer(valueSerializer);

    template.setConnectionFactory(lettuceConnectionFactory());
    // Invoked by the containing BeanFactory after it has
    // set all bean properties and satisfied BeanFactoryAware,
    // ApplicationContextAware etc.

    //This method allows the bean instance to perform
    // validation of its overall configuration and
    // final initialization when all bean properties have been set.
    template.afterPropertiesSet();
    return template;
  }

  @Bean
  public RedisCacheConfiguration cacheConfig(CacheConfigProperty property) {
    return createCacheConfiguration(property.getTimeToLive() * 60);
  }

  @Bean
  public CacheManager cacheManager(
    RedisConnectionFactory lettuceConnectionFactory, CacheConfigProperty property) {
    Map<String, RedisCacheConfiguration> configMap = new HashMap<>();

    for(Map.Entry<String, Long> nameAndTimeout: property.getCacheExpiration().entrySet()) {
      configMap.put(nameAndTimeout.getKey(), createCacheConfiguration(nameAndTimeout.getValue()));
    }

    return RedisCacheManager
      .builder()
      .cacheDefaults(cacheConfig(property))
      .withInitialCacheConfigurations(configMap)
      .cacheWriter(RedisCacheWriter.nonLockingRedisCacheWriter(lettuceConnectionFactory))
      .build();
  }
}
