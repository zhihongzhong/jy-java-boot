package com.example;

import com.example.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class RedisUtilTest {
  @Autowired
  private RedisUtil redisUtil;

  @Test
  public void testRedisUtil() {
    redisUtil.put("hello", "123", 100);
    String value = (String) redisUtil.get("hello");
    assertEquals(value, "123");
  }

}
