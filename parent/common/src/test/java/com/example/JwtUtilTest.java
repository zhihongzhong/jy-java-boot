package com.example;


import com.example.config.jwt.JwtProvider;
import com.example.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class JwtUtilTest {

  @Autowired
  private JwtProvider provider;

  private Logger logger = LoggerFactory.getLogger(JwtUtilTest.class);

  @Test
  public void testJwtToken() {
    String userName = "ZzH_123456";

    String token = provider.genToken(userName);
    logger.info("got token: [{}]",token);
    assertNotNull(token);
    assertNotEquals(token, "");

    String decodedUsername = provider.getSubject(token);
    assertEquals(userName, decodedUsername);

    Map<String, String> extraAttrs = new HashMap<>();
    extraAttrs.put("password", "Qin");
    extraAttrs.put("sub", "Quin");

    token  = provider.genToken(userName, extraAttrs);
    decodedUsername = provider.getSubject(token);
    assertEquals(userName, decodedUsername);
  }
}
