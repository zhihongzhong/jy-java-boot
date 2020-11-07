package com.example.system.security.filter;

import com.example.common.config.jwt.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

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
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  @Autowired
  private final JwtProvider jwtProvider;

  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

  public JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, JwtProvider jwtProvider) {
    super(requiresAuthenticationRequestMatcher);
    this.jwtProvider = jwtProvider;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                              HttpServletResponse httpServletResponse)
    throws AuthenticationException, IOException, ServletException {
    String username = jwtProvider.getSubject(httpServletRequest);
    logger.info("进入鉴权模块， token [{}]", username);
    return null;
  }
}
