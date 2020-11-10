package com.example.config.security.filter;

import com.example.common.config.jwt.JwtConfigProperty;
import com.example.common.config.jwt.JwtProvider;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * this filter is used for spring security config to replace the UsernamePasswordFilter
 * pass a Matcher that requires to be filtered into super class.
 *
 * override attemptAuthentication.
 *
 * @author ZzH
 * @since 2020.11.05
 * */
@Log4j2
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


  private final JwtProvider jwtProvider;

  /* jwt property */
  private final JwtConfigProperty property;
  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  public JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher,
                                 JwtProvider jwtProvider, JwtConfigProperty property,
                                 AuthenticationManager authenticationManager) {

    super(requiresAuthenticationRequestMatcher);
    setAuthenticationManager(authenticationManager);
    this.jwtProvider = jwtProvider;
    this.property = property;

  }

  /** 尝试用户登录， 利用 authenticationManager.authenticate 方法对用户进行登录 */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                              HttpServletResponse httpServletResponse)
    throws AuthenticationException, IOException, ServletException {
    String token = httpServletRequest.getHeader(property.getBearer());

    /* 返回 null 会导致 doFilter return */
    if(token == null) {
      log.info("未找到token，将抛出异常, bearer 值：[{}]", property.getBearer());
      throw new UsernameNotFoundException("token 未找到");
    }
    String username = jwtProvider.getSubject(token);
    String password = (String) jwtProvider.getClaim(token, property.getPasswordField());

    log.info("拿到用户名： [{}], 密码： [{}]", username, password);

    Authentication authentication = new UsernamePasswordAuthenticationToken(username, "qingtian");
    return getAuthenticationManager().authenticate(authentication);
  }

  /**
   *  认证失败的时候进入到这个方法
   **/
  @Override
  public void unsuccessfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response,
                                         AuthenticationException exception)
    throws IOException, ServletException {
    log.info("认证失败，即将退出");
    super.unsuccessfulAuthentication(request,response, exception);
  }

  @Override
  public void successfulAuthentication(HttpServletRequest request,
                                       HttpServletResponse response,
                                       FilterChain chain,
                                       Authentication authentication)
    throws IOException, ServletException {
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
    log.info("认证成功！");
  }
}
