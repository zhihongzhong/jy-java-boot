package com.example;

import com.example.common.utils.UUIDUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class UUIDUtilTest {

  @Test
  public void testUuidLength() {
    String uuid = UUIDUtil.uuid();
    String uuid2 = UUIDUtil.uuid();
    assertEquals(uuid.length(), 32);
    assertEquals(uuid2.length(), 32);
    assertNotEquals(uuid, uuid2);
  }
}
