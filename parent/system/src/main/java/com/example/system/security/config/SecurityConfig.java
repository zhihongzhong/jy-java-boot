package com.example.system.security.config;

import com.example.common.config.jwt.JwtProvider;
import com.example.system.security.filter.JwtAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtProvider jwtProvider;
  private final String UNLIMITED_URI = "/sys/**";
  private final String LOGIN_URI = "/sys/login";

  private final String[] IGNORED_URIs = {"/v2/api-docs/**", "/doc.html", "/webjars/**"};

  public SecurityConfig(JwtProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .csrf()
      .disable()
      .addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
      .authorizeRequests()
      .antMatchers(UNLIMITED_URI)
      .permitAll()
      .anyRequest()
      .authenticated();
  }

  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    AntPathRequestMatcher matcher = new AntPathRequestMatcher(UNLIMITED_URI);
    NegatedRequestMatcher requestMatcher = new NegatedRequestMatcher(matcher);
    return new JwtAuthenticationFilter(requestMatcher, this.jwtProvider);
  }


  @Override
  public void configure(WebSecurity webSecurity) {
    webSecurity.ignoring().antMatchers(IGNORED_URIs);
  }

}
