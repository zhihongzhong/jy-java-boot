package com.example.config.jwt;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtConfigProperty.class)
@ComponentScan
public class JwtConfig {

  private final JwtConfigProperty jwtConfigProperty;
  public JwtConfig(JwtConfigProperty jwtConfigProperty) {
    this.jwtConfigProperty = jwtConfigProperty;
  }

  @Bean
  public JwtProvider jwtProvider() {
    return new JwtProvider(this.jwtConfigProperty);
  }
}
