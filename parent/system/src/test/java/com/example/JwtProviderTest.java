package com.example;

import com.example.common.config.jwt.JwtProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  SystemApplication.class)
public class JwtProviderTest {

  @Autowired
  private JwtProvider provider;
  @Test
  public void testJwtProvider() {
    String userName = "ZzH_123456";

    String token = provider.genToken(userName);
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
