package com.example;

import com.example.common.utils.MD5Util;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

@SpringBootTest
public class MD5UtilTest {

  @Test
  public void testMd5Generation() {
    String plainText = "123456";
    String expectedCipher = "e10adc3949ba59abbe56e057f20f883e";
    String encodedCipher = MD5Util.MD5Encode(plainText, "");
    assertEquals(expectedCipher, encodedCipher);

    plainText = "";
    encodedCipher = MD5Util.MD5Encode(plainText, "");
    expectedCipher = "d41d8cd98f00b204e9800998ecf8427e";
    assertEquals(expectedCipher, encodedCipher);
  }
}
