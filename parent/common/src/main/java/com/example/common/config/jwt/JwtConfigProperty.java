package com.example.common.config.jwt;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * jwt 配置文件
 * @author ZzH
 * @since 2020.11.05
 * */
@Data
@Configuration
@Primary
@ConfigurationProperties(prefix = "jy.jwt")
public class JwtConfigProperty {

  private long expiration = 24 * 60 * 60 * 1000; // 毫秒为单位

  private String bearer = "bearer "; // 认证消息头

  private String secretKey = "hello_world";

}
