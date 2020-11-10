package com.example.config.security.config;

import com.example.common.config.jwt.JwtConfigProperty;
import com.example.common.config.jwt.JwtProvider;
import com.example.config.security.api.JwtPasswordEncoder;
import com.example.config.security.api.JwtUserDetailsService;
import com.example.config.security.filter.JwtAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtProvider jwtProvider;

  /* 用于 HTTP_SECURITY 忽略 */
  private final String UNLIMITED_URI = "/sys/auth/**";
  private final String LOGIN_URI = "/sys/auth/login";

  /* 用于 WEB_SECURITY 忽略， WEB_SECURITY 优先级高于 HTTP_SECURITY  */
  private final String[] IGNORED_URIs = {"/v2/api-docs/**", "/doc.html", "/webjars/**", "/swagger-resources/**", "/error/**", "/swagger-ui.html"};

  /* admin 的 HTTP_SECURITY URL */
  private final String ADMIN_URL = "/sys/admin";

  private final JwtConfigProperty jwtConfigProperty;

  /* 用于给系统自带的 authenticationManager 调用 */
  private final UserDetailsService userDetailsService;

  public SecurityConfig(JwtProvider jwtProvider, JwtConfigProperty jwtConfigProperty, JwtUserDetailsService userDetailsService) {
    this.jwtProvider = jwtProvider;
    this.jwtConfigProperty = jwtConfigProperty;
    this.userDetailsService = userDetailsService;
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
      .antMatchers(ADMIN_URL)
      .hasRole("admin")
      .anyRequest()
      .authenticated();
  }

  public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
    AntPathRequestMatcher matcher = new AntPathRequestMatcher(UNLIMITED_URI);
    NegatedRequestMatcher requestMatcher = new NegatedRequestMatcher(matcher);
    return new JwtAuthenticationFilter(requestMatcher, jwtProvider, jwtConfigProperty, authenticationManager());
  }


  @Override
  public void configure(WebSecurity webSecurity) {
    webSecurity.ignoring().antMatchers(IGNORED_URIs);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(new JwtPasswordEncoder());
  }
}
