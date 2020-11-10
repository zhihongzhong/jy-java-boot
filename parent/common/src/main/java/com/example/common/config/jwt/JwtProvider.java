package com.example.common.config.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 认证相关功能类
 * @author ZzH
 * @since 2020.11.05
 * */
public class JwtProvider {

  private static final Logger logger =
    LoggerFactory.getLogger(JwtProvider.class);

  private final String secretKey;
  private final Long expiration;
  private final String bearer;

  public JwtProvider(JwtConfigProperty property) {
    BeanWrapper propertyWrapper = new BeanWrapperImpl(property);
    this.secretKey = (String) propertyWrapper.getPropertyValue("secretKey");
    this.expiration = (Long) propertyWrapper.getPropertyValue("expiration");
    this.bearer = (String) propertyWrapper.getPropertyValue("bearer");
  }

  public String getSubject(String token) {
    if(token == null) {
      logger.info("token 为空！");
      return null;
    }

    try {
      return Jwts.parser()
        .setSigningKey(secretKey.getBytes())
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
    }catch(Exception e) {
      logger.info("解析token失败！");
      return null;
    }
  }

  public String getSubject(HttpServletRequest request) {
    final String token = request.getHeader(bearer);
    return getSubject(token);
  }
  /**
   * 根据给定用户名以及额外参数生成Token
   * @param userName 用户名
   * @param extraAttrs 额外参数
   * @return Token
   * */
  public String genToken(String userName, Map<String, String> extraAttrs) {
    Date expirationTime = new Date(System.currentTimeMillis() + expiration);

    JwtBuilder jwtBuilder = Jwts.builder();
    jwtBuilder.setSubject(userName);
    jwtBuilder.signWith(SignatureAlgorithm.HS512, secretKey.getBytes());

    for(Map.Entry<String, String> pair : extraAttrs.entrySet()) {
      if(pair.getKey().equals("sub")) continue;
      jwtBuilder.claim(pair.getKey(), pair.getValue());
    }

    jwtBuilder.setExpiration(expirationTime);
    return jwtBuilder.compact();
  }

  public String genToken(String userName) {
    Date expirationTime = new Date(System.currentTimeMillis() + expiration);

    return Jwts
      .builder()
      .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
      .setSubject(userName)
      .setExpiration(expirationTime)
      .compact();
  }

  public Object getClaim(String token, String claimName) {
    try {
      return Jwts
        .parser()
        .setSigningKey(secretKey.getBytes())
        .parseClaimsJws(token)
        .getBody()
        .get(claimName);
    }catch (Exception e) {
      return Boolean.FALSE;
    }

  }
}
