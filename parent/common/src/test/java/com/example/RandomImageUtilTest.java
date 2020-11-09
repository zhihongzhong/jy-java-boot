package com.example;

import com.example.common.utils.RandomImageUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.Assert.assertTrue;
@SpringBootTest
public class RandomImageUtilTest {

  @Test
  public void testImageGeneration() {

    try {
      String randomCode = Integer.toString(1234);
      String base64Image = RandomImageUtil.generateBase64(randomCode);
      assertTrue(Boolean.TRUE);
    }catch (IOException e) {
      assertTrue(Boolean.FALSE);
    }

  }
}
